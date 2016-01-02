/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcmiddleearth.minigames.command;

import com.mcmiddleearth.minigames.data.PluginData;
import com.mcmiddleearth.minigames.game.AbstractGame;
import com.mcmiddleearth.minigames.game.GameType;
import com.mcmiddleearth.minigames.game.QuizGame;
import com.mcmiddleearth.minigames.utils.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Eriol_Eandur
 */
public class QuizGameRandom extends AbstractGameCommand{
    
    public QuizGameRandom(String... permissionNodes) {
        super(0, true, permissionNodes);
        setShortDescription(": ");
        setUsageDescription(": ");
    }
    
    @Override
    protected void execute(CommandSender cs, String... args) {
        AbstractGame game = getGame((Player) cs);
        if(game != null && isManager((Player) cs, game) 
                        && isCorrectGameType((Player) cs, game, GameType.LORE_QUIZ)) {
            QuizGame quizGame = (QuizGame) game;
            if(args.length>0 && args[0].equalsIgnoreCase("off")) {
                quizGame.setRandom(false, false);
                sendRandomOffMessage(cs);
            }
            else if(args[0].equalsIgnoreCase("questions")) {
                quizGame.setRandom(true,false); 
                sendRandomQuestionsMessage(cs);
            }
            else if(args[0].equalsIgnoreCase("choices")) {
                quizGame.setRandom(false,true); 
                sendRandomChoicesMessage(cs);
            }
            else {
                quizGame.setRandom(true,true);
                sendRandomAllMessage(cs);
            }
        }
    }

    private void sendNoWinnerMessage(CommandSender cs) {
        MessageUtil.sendErrorMessage(cs, "There is no winner.");
    }

    private void sendRandomOffMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "Questions and choices will be presented in proper order.");
    }

    private void sendRandomQuestionsMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "Questions will be sended in random order.");
    }
    
    private void sendRandomChoicesMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "Choices will be presented in random order.");
    }
    
    private void sendRandomAllMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "Questions and Choises will be presented in random order.");
    }
}