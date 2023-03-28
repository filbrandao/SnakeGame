package academy.mindswap;

public enum SoundFiles {

    GAME_LOOP("resources/soundEffects/8bit-music-for-game-68698.wav"),
    FRUIT("resources/soundEffects/mixkit-retro-game-notification-212.wav"),
    GAME_OVER_STRING("resources/soundEffects/astronaut-says-game-over-73039.wav"),
    GAME_OVER_SOUND("resources/soundEffects/pixel-death-6682.wav");
    private final String path;

    SoundFiles(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
}
