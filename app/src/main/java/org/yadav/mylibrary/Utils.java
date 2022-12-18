package org.yadav.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVOURITE_BOOKS = "favourite_books";


    private static Utils instance;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor = sharedPreferences.edit();
    Gson gson = new Gson();

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favouriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if(null == getAllBooks()){
            initData();
        }
        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        };
        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavouriteBooks()) {
            editor.putString(FAVOURITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }

    private void initData(){

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://m.media-amazon.com/images/I/41FdmYnaNuL.jpg", "A work of maddening brilliance", "A young woman named Aomame follows a taxi driver’s enigmatic suggestion and begins to notice puzzling discrepancies in the world around her. She has entered, she realizes, a parallel existence, which she calls 1Q84 —“Q is for ‘question mark.’ A world that bears a question.” Meanwhile, an aspiring writer named Tengo takes on a suspect ghostwriting project. He becomes so wrapped up with the work and its unusual author that, soon, his previously placid life begins to come unraveled. \n"));
        books.add(new Book(2, "Hobbit", "J.R.R.Tolkein", 304, "https://m.media-amazon.com/images/I/710+HcoP38L.jpg", "The Hobbit is a wondrous tale of adventure and heroism set in the fantasy realm of middle-earth.", "In a hole in the ground there lived a hobbit. Not a nasty, dirty, wet hole, filled with the ends of worms and an oozy smell, nor yet a dry, bare, sandy hole with nothing in it to sit down on or to eat: it was a hobbit-hole, and that means comfort. Written for J.R.R. Tolkien’s own children, The Hobbit met with instant critical acclaim when it was first published in 1937. Now recognized as a timeless classic, this introduction to the hobbit Bilbo Baggins, the wizard Gandalf, Gollum, and the spectacular world of Middle-earth recounts of the adventures of a reluctant hero, a powerful and dangerous ring, and the cruel dragon Smaug the Magnificent. The text in this 372-page paperback edition is based on that first published in Great Britain by Collins Modern Classics (1998), and includes a note on the text by Douglas A. Anderson (2001)"));
        books.add(new Book(3, "Dune", "Frank Herbert", 658, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1555447414l/44767458.jpg", "When House Atreides is betrayed, the destruction of Paul’s family will set the boy on a journey toward a destiny greater than he could ever have imagined.", "Set on the desert planet Arrakis, Dune is the story of the boy Paul Atreides, heir to a noble family tasked with ruling an inhospitable world where the only thing of value is the “spice” melange, a drug capable of extending life and enhancing consciousness. Coveted across the known universe, melange is a prize worth killing for...\nWhen House Atreides is betrayed, the destruction of Paul’s family will set the boy on a journey toward a destiny greater than he could ever have imagined. And as he evolves into the mysterious man known as Muad’Dib, he will bring to fruition humankind’s most ancient and unattainable dream."));
        books.add(new Book(4, "The Stand", "Stephen King", 1152, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1213131305l/149267.jpg", "Stephen King's apocalyptic vision of a world blasted by plague and tangled in an elemental struggle between good and evil remains as riveting and eerily plausible as when it was first published.", "A patient escapes from a biological testing facility, unknowingly carrying a deadly weapon: a mutated strain of super-flu that will wipe out 99 percent of the world's population within a few weeks. Those who remain are scared, bewildered, and in need of a leader. Two emerge - Mother Abagail, the benevolent 108-year-old woman who urges them to build a peaceful community in Boulder, Colorado; and Randall Flagg, the nefarious \"Dark Man,\" who delights in chaos and violence. As the dark man and the peaceful woman gather power, the survivors will have to choose between them - and ultimately decide the fate of all humanity."));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();

    }

    public static Utils getInstance(Context context) {
        if (null != instance) return instance;
        instance = new Utils(context);
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS, null), type);
        return books;
    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if(books != null) {
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null){
            if (books.add(book)){
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return  true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null){
            if (books.add(book)){
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return  true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null){
            if (books.add(book)){
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return  true;
            }
        }
        return false;
    }

    public boolean addToFavourite(Book book) {
        ArrayList<Book> books = getFavouriteBooks();
        if (books != null){
            if (books.add(book)){
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean deleteFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromCurrentlyRead(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null){
            for (Book b : books){
                if (b.getId() == book.getId()){
                    if(books.remove(b)){
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null){
            for (Book b : books){
                if (b.getId() == book.getId()){
                    if(books.remove(b)){
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromFavourite(Book book) {
        ArrayList<Book> books = getFavouriteBooks();
        if (books != null){
            for (Book b : books){
                if (b.getId() == book.getId()){
                    if(books.remove(b)){
                        editor.remove(FAVOURITE_BOOKS);
                        editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
