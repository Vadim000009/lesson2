package com.beernetwork.web.app.dao;

public class MessengesDAO {

    // Создание таблицы, если ранее не говорили
    // CREATE TABLE inc+MESSAGES (numberLetter INT PRIMARY_KEY AUTO_INCREMENT, idFstUser INT NOT NULL, idSecUser INT NOT NULL,idWhoSend INT NOT NULL, message TEXT, FOREIGN HEY(idFstUser) REFERENCES USER(id), FOREIGN HEY(idSecUser) REFERENCES USER(id));

//    public boolean createNewMessage (Message message) {
//        Integer idFstUser = message.getForeignKeySender(), idSecUser = message.getForeignKeyRecipient();
//
//    }
    // Отправка
    // INSERT INTO inc+MESSAGES (idWhoSenc, message) values ('idSendler','message')

//    public Message getMessageFromDB() {

//    }
//    public Message getManyMessagesFromDB(){

//    }
    // Получение (так же принудительно)
    // SELECT * FROM inc+MESSAGES where ID = Max(ID) LIMIT 50;

    // Заход на страниицу
    // SELECT * FROM * where idFstUser|| idSecUser = id

}
