package srb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import srb.telegram.bot.SrBTelegramBot;

@RestController
public class TelegramBotController {


//	@RequestMapping(value = "/tlg", method = RequestMethod.GET)
//	@ResponseBody
//	public  String tlg() {
//		
//        TelegramBotsApi botsApi;
//		try {
//			botsApi = new TelegramBotsApi(DefaultBotSession.class);
//			botsApi.registerBot(new SrBTelegramBot());	
//		} catch (TelegramApiException e1) {
//			e1.printStackTrace();
//		}
//		
//		return "Ok";
//	}	
//	
}
