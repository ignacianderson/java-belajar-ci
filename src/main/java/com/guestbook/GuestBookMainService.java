package com.guestbook;

import com.google.gson.Gson;
import com.guestbook.controllers.GuestBookController;
import com.guestbook.controllers.GuestBookTransformer;
import com.guestbook.repositories.GuestBookRepository;
import com.guestbook.services.GuestBookService;

import static spark.Spark.port;
import static spark.Spark.post;

public class GuestBookMainService {

    public static void main(String[] args) {

        Gson gson = new Gson();
        GuestBookRepository guestBookRepository = new GuestBookRepository();
        GuestBookService guestBookService = new GuestBookService(guestBookRepository);
        GuestBookController guestBookController = new GuestBookController(gson, guestBookService);
        GuestBookTransformer transformer = new GuestBookTransformer();

        port(4000);
        post("/internal/post/comment", guestBookController::save, transformer);
    }

}