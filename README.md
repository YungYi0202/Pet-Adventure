# FOOP Final Project
# Pet-Adventure
## The team members’ names and school IDs
- B08902029 陳咏誼
- B08902035 彭光湞
- B08902036 楊鈞安
- B08902057 陳奕瑄

## How you divide the responsibilities of the team members

- 陳咏誼
	- 遊戲主架構設計、關卡架構設計、Menu設計、道具設計、音樂
		- `controller/`, `views/`, `menu/`, `World`, `Stage`, `Sprite`, `SerialAlphabet`, `Alphabet`,`Candy`,`Hydrant`, `AudioPlayer` , `StageManager`
	- 製作class關係圖（報告 "The relations between the classes that you design" 部份）
- 彭光湞
	- 道具、地板、障礙物、關卡、及部分 State 等設計
		- `Bird`, `ChargeCan`, `Farground`, `Ground`, `Hole`, `StageEasy`, `StageHard`, `State`
	- Demo
- 楊鈞安
    - Pet 本體的架構, State 和 PropState 控制
        - `State`, `pet/`, `DoubleCan`, `SheildCan`
    - 撰寫報告 "The advantages and disadvantage of your design" 部分
- 陳奕瑄
  - ImageIO，ImageState 控制及繪圖
      - `assets/`, `utils/`, 所有`ImageState` 的 children
  - 撰寫報告 "Other packages that you have used" 及 "How to play your 2D game" 部份

## The relations between the classes that you design
* 箭頭方向為父類別指向子類別
* state, object資料夾下類別繁多，沒有全部顯示在圖上

![](https://i.imgur.com/Gev91QE.png)

## The advantages of your design
### OOP part
* 新增角色只需要角色的圖檔，不需更改主架構也不需新增多的class。
* 使用 3 種 state：
    * `State` 控制好pet的行為狀態。
    * `PropState` 控制道具使用的狀態。
    * `ImageState` 控制好 pet 的動作圖。
* 使用道具不必額外定義新的`state`，原本跑跳的動作不會受到影響。
* 角色的 state 不需因增減道具而修改。
* 利用`State` call `ImageState`維護動作圖，讓`State`在切換時不必擔心動作圖片的更換。
* 可以任意地在關卡中增添障礙物和道具。
* 整體遊戲流程利用ㄧ個`GameLoop`維護，並且利用`World`這個class包裝整個遊戲過程內容物，再利用`GameView`維護整個遊戲的畫面。
* 利用`prop`class 包裝所有的道具和障礙物，只需要小改各自的`collideWith`，就能發揮各自的功用。
* 新增關卡只需要新增一個`stage/`裡的class，運用`Stage`裡的API就可以設置所有object的位置，再將它寫進`StageManager`即可。

### Gaming quality part
* 所有遊戲內的背景和圖片都相當精緻，九成以上的圖皆自行繪製。
* 整體遊戲流程流暢。
* 利用`imagestate` 維護要 render 的圖片，使得動作和動作之間的切換流暢，例如：跳躍和下滑皆有連續動作，像連續動畫一樣。
* 利用 `collideWith` function 維護遊戲中吃到道具和撞到障礙物的行為，也利用重力加速度和地板的`collideWith` function 維護好pet在遊戲中跑跳的物理行為。
* 設計了封面選關選角，使得玩家有更多遊玩選擇，且關卡和角色皆可擴充，只要有新的角色圖檔，就能遊玩新的角色。
* 設置暫停頁面，讓玩家可以暫停喘口氣或是重新遊玩。
* 設有背景音樂和各式音效，提升玩家遊戲體驗。
## The disadvantages of your design
* `menu/` 裡的各個 class 比較沒有統整性。
* `ImageIo` 裡的`path` 寫得太死。
* `Pet` 的內容過多，不方便閱讀。  
#### 未來可以改進的部分：
* `menu/` 可以 implement 同一個 interface，再包裝得更好一點。
* Pet 裡處理的事情過多，可以再拆分出來。
* 因為有計分機制，可以 deploy 到網路上，結合資料庫，做出計分表。
* 遊戲劇情表達不足
* 未來可以新增2P
## Other packages that you have used
- `java.lang`
- `java.awt`
- `java.util`
- `java.io`
- `java.nio`
- `javx.swing`
- `javax.imageio`
- `javax.sound`
## How to play your 2D game

### 背景故事
小寵物跟小主人走丟了！還好小主人是個愛吃鬼，路上掉了許多餅乾，你可以協助小寵物沿著餅乾路線，避開路上的障礙找回小主人嗎？
  
### 操作方法
- ![](https://i.imgur.com/k1yFJpo.png =30x) ：跳躍。
- ![](https://i.imgur.com/zImomMZ.png =30x) ：滑行。
- ![](https://i.imgur.com/xBJD6Du.png =30x) ：釋放道具。

### 遊戲物件
- 小寵物
    - ![](https://i.imgur.com/n3ZJa6n.png =70x)　：小狗狗，性格開朗，愛撒嬌。

    - ![](https://i.imgur.com/5fJ5CrD.png =70x)　：小貓貓，喜歡耍帥，有點傲嬌。

- 餅乾
    - ![](https://i.imgur.com/1cWlNvV.png =40x)  ：一般餅乾，吃了可以加 30 分。
    - ![](https://i.imgur.com/DRgVOnV.png =40x)  ：字母餅乾，吃了可以加 100 分。湊齊字母後可以再加 300 分。
- 道具
    - ![](https://i.imgur.com/WacWDE6.png =40x) ：加速道具，吃了可以加速，並進入無敵狀態。
    - ![](https://i.imgur.com/PeH7ksL.png =40x) ：防護罩道具，吃了可以撞到障礙物一次不扣血。
    - ![](https://i.imgur.com/wLUUcKp.png =40x) ：雙倍道具，吃了之後得到的分數會雙倍。
- 障礙物
    - ![](https://i.imgur.com/A7ETgam.png =40x) ：鳥，撞上會扣血。
    - ![](https://i.imgur.com/csnMGjK.png =40x) ：消防栓，撞上會扣血。
- 欄杆：
    - ![](https://i.imgur.com/DHbbKGQ.png =100x)![](https://i.imgur.com/9NFTbXK.png =100x)　：欄杆，可以跳上去。
- 洞
    - ![](https://i.imgur.com/1OLMvyb.png =100x)![](https://i.imgur.com/Frp26Uo.png =100x)　：洞，掉入即死亡。

### 遊戲流程
- 主選單

  選擇關卡難度及小寵物。

  ![](https://i.imgur.com/Mn6MuYE.png)


- 教學頁

  ![](https://i.imgur.com/WitTrZs.png)


- 遊戲畫面

   ![](https://i.imgur.com/PdiSlPk.png)

- 暫停

  ![](https://i.imgur.com/re8CCvm.png)

- 失敗

   ![](https://i.imgur.com/y8wYt5U.png)

- 找到小主人了！

 ![](https://i.imgur.com/0pshVLm.png)

## Music Resource (All Free)
* Game Music:
Monkeys Spinning Monkeys Kevin MacLeod (incompetech.com)
Licensed under Creative Commons: By Attribution 3.0 License
http://creativecommons.org/licenses/by/3.0/
Music promoted by https://www.chosic.com/
* Menu Music:
Fluffing a Duck Kevin MacLeod (incompetech.com)
Licensed under Creative Commons: By Attribution 3.0 License
http://creativecommons.org/licenses/by/3.0/
Music promoted by https://www.chosic.com
* Other Sound Effect:
https://freesound.org/

## YouTube Link
https://youtu.be/KXZLSZYpVmA