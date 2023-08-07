package com.ambrosia.gerbilhelp.window;

import java.awt.*;

/**
 * This class was made because I didn't want to deal with Java Swing's silly layout system.
 */
public class CustomLayout {
	public int currentPosX;
	public int currentPosY;

	private final int startingPosX;
	private final int startingPosY;

	public final int paddingX;
	public final int paddingY;

	public CustomLayout(int startingPosX, int startingPosY, int paddingX, int paddingY) {
		this.startingPosX = startingPosX;
		this.startingPosY = startingPosY;

		this.currentPosX = startingPosX;
		this.currentPosY = startingPosY;

		this.paddingX = paddingX;
		this.paddingY = paddingY;
	}

	public Container manage(Container container, int sizeX, int sizeY, boolean stepX, boolean stepY) {
		container.setBounds(currentPosX, currentPosY, sizeX, sizeY);

		if (stepX) currentPosX += sizeX + paddingX;
		if (stepY) currentPosY += sizeY + paddingY;

		return container;
	}

	public void nextLineX(int size) {
		currentPosX += size + paddingX;
		currentPosY = this.startingPosY;
	}

	public void nextLineY(int size) {
		currentPosY += size + paddingY;
		currentPosX = this.startingPosX;
	}
}
