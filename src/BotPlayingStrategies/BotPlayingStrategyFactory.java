package BotPlayingStrategies;

import Models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy BotPlayinngStrategyDiffcultyLevel(BotDifficultyLevel botDifficultyLevel)
    {
        return new EasyBotPlayingStrategy();
//        return switch (botDifficultyLevel)
//        {
//            case EASY -> new EasyBotPlayingStrategy();
//            case HARD -> new HardBotPlayingStrategy();
//            case MEDIUM -> new MediumBotPlayingStrategy();
//        };
    }
}
