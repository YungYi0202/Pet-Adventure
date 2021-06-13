package state;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author - Leyna
 */

public abstract class ImageState{
	private ArrayList<ImageFrame> frames;
	private int currFrameIndex; // index of current frame
	private long animTime; // current time in animation state
	private long totalDuration; // length of entire animation state
	private long defaultAnimLength;

	public ImageState addFrame(BufferedImage image, long duration) {
		totalDuration += duration;
		frames.add(new ImageFrame(image, totalDuration));
		return this;
	}

	public void start() {
		animTime = 0;
		currFrameIndex = 0;
	}
	
	// get the height of the current animation state in pixels.
	public int getHeight() {
		return getFrame(currFrameIndex).image.getHeight();
	}
	
	// get the width of the current animation state in pixels.
	public int getWidth() {
		return getFrame(currFrameIndex).image.getWidth();
	}
	
	// Updates the animation state by a give amount of time.
	// The frames are updated accordingly to the internal timer of this animation state.
    public void update() {
        if (frames.size() > 1) { // must have at least 2 frames to animate
            ++animTime; // animation state time increase here

            if (animTime >= totalDuration) { //reset animation state
            	animTime = 0; 
                currFrameIndex = 0; // back to first frame                
            }
            if(animTime > getFrame(currFrameIndex).endTime) {
                currFrameIndex++;
            }
        }
    }
    
    public BufferedImage getImage() {
        if (frames.size() == 0) {
            return null;
        }
        else {
            return getFrame(currFrameIndex).image;
        }
    }

    private ImageFrame getFrame(int i) {
        return frames.get(i);
    }

    private class ImageFrame {
    	Image image;
        long endTime; // the totalDuration up to the end of this frame is the endTime for this frame
 
        public ImageFrame(BufferedImage image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }

}