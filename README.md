## Telegram HLTV Bot

This project is based on [HLTV API](https://github.com/dajk/hltv-api)  - An unofficial JSON api for popular CS:GO website hltv.org.

As for me - it is a pet project for practice Spring, Json, communication with other APIs, Telegram Bot practice and improving my skills in Java.

###What bot can do?
1. `News` - displays summary of news from hltv.org.\
   Every message with news also have inline button which have link to hltv.org with this news.
   
2. `Results` - displays all results for teams, played in last 5 days.\
   Every message with result also have inline button which have link to hltv.org with game results.
    
3. `/teams` - displays list of teams as InlineKeyboard. Pressing the team button show's you team games results in last 5 days.\
       Every message with result also have inline button which have link to hltv.org with game results.


###For successful start bot You need to create `application-botSettings.properties` with following strings:

| Filed Name | Description |
| :---: | :---: |
|`telegramHLTVBotToken`|  `your's telegram bot token`
|`telegramHLTVBotUsername`|  `your's bot username` |
|`telegramHLTVBotPath`|  `webhook path` |
