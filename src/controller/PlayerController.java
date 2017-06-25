package controller;

import view.PlayerView;

@SuppressWarnings("unused")
public class PlayerController {
	private PlayerView playerView;
	private MainController mainController;
	
	public PlayerController(PlayerView playerView, MainController mainController) {
		this.playerView = playerView;
		this.mainController = mainController;
	}
}
