package org.yadav.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private ImageView bookImage;
    private TextView txtBookName, txtAuthor, txtPages, txtDesc;
    private Button btnAddWantToRead, btnAddCurrentlyReading, btnAddAlreadyRead, btnAddFavourite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(null != incomingBook){
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToRead(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFavourite(incomingBook);
                }
            }
        }
    }

    private void handleAlreadyRead(Book book){
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;
        for(Book b : alreadyReadBooks){
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
                break;
            }
        }
        if (existInAlreadyReadBooks){
            btnAddAlreadyRead.setEnabled(false);
        } else{
            btnAddAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try once again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToRead(Book book){
        ArrayList<Book> wantToRead = Utils.getInstance(this).getWantToReadBooks();
        boolean existInWantToReadBooks = false;
        for(Book b : wantToRead){
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
                break;
            }
        }
        if (existInWantToReadBooks){
            btnAddWantToRead.setEnabled(false);
        } else{
            btnAddWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try once again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReading(Book book){
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for(Book b : currentlyReadingBooks){
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
                break;
            }
        }
        if (existInCurrentlyReadingBooks){
            btnAddCurrentlyReading.setEnabled(false);
        } else{
            btnAddCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try once again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavourite(Book book){
        ArrayList<Book> favouriteBooks = Utils.getInstance(this).getFavouriteBooks();
        boolean existInFavouriteBooks = false;
        for(Book b : favouriteBooks){
            if (b.getId() == book.getId()) {
                existInFavouriteBooks = true;
                break;
            }
        }
        if (existInFavouriteBooks){
            btnAddFavourite.setEnabled(false);
        } else{
            btnAddFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavourite(book)){
                        Toast.makeText(BookActivity.this, "Book Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavouriteBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try once again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDesc.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);

    }

    private void initViews(){
        bookImage = findViewById(R.id.imageBook);

        txtBookName = (TextView) findViewById(R.id.txtBkName);
        txtAuthor = (TextView) findViewById(R.id.txtAuthName);
        txtPages = (TextView) findViewById(R.id.txtPageCount);
        txtDesc = (TextView) findViewById(R.id.txtLongDesc);

        btnAddCurrentlyReading = (Button) findViewById(R.id.btnAddToCurrentlyReading);
        btnAddWantToRead = (Button) findViewById(R.id.btnAddToWantToRead);
        btnAddAlreadyRead = (Button) findViewById(R.id.btnAddToAlreadyRead);
        btnAddFavourite = (Button) findViewById(R.id.btnAddToFavourites);
    }
}