package com.example.interviewapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.interviewapp.retrofit.retrofit_model.ProductInfoDetail;
import com.example.interviewapp.retrofit.retrofit_model.ProductListResponseSearchMobile;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productManager";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_PRODUCTS_DETAILS = "details";
    private static final String KEY_ID = "id";
    private static final String KEY_SOCIETYPRODUCTID = "SocietyproductID";
    private static final String KEY_TITLE = "Title";
    private static final String KEY_ORIGINAL_IMAGE = "OriginalImage";
    private static final String KEY_THUMB_IMAGE = "ThumbImage";
    private static final String KEY_THUMB_IMAGE_BLOB = "ThumbImageBlob";
    private static final String KEY_FINAL_PRICE = "Finalprice";
    private static final String KEY_MRP = "Mrp";
    private static final String KEY_CARTQUANTITY = "CartQuantity";
    private static final String KEY_TOTALCOUNT = "TotalCount";
    private static final String KEY_CARTID = "CartId";
    private static final String KEY_CARTCOUNT = "CartCount";
    private static final String KEY_UNIT = "Unit";
    private static final String KEY_CATEGORY_ID = "CategoryID";
    private static final String KEY_SUB_CATEGORY_ID = "SubcategoryID";
    private static final String KEY_CATEGORY_TITLE = "CategoryTitle";
    private static final String KEY_SUB_CATEGORY_TITLE = "SubCategoryTitle";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_ADDED_TO_CART = "AddedtoCart";
    private static final String KEY_PRODUCT_ID = "ProductID";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_PRODUCTS_DETAILS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS_DETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SOCIETYPRODUCTID + " TEXT,"
                + KEY_PRODUCT_ID + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_CATEGORY_ID + " TEXT,"
                + KEY_SUB_CATEGORY_ID + " TEXT,"
                + KEY_CATEGORY_TITLE + " TEXT,"
                + KEY_SUB_CATEGORY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_MRP + " TEXT,"
                + KEY_FINAL_PRICE + " TEXT,"
                + KEY_UNIT + " TEXT,"
                + KEY_ADDED_TO_CART + " TEXT,"
                + KEY_CARTID + " TEXT,"
                + KEY_CARTQUANTITY + " TEXT,"
                + KEY_CARTCOUNT + " TEXT"
                + ")";

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SOCIETYPRODUCTID + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_ORIGINAL_IMAGE + " TEXT,"
                + KEY_THUMB_IMAGE + " TEXT,"
                + KEY_THUMB_IMAGE_BLOB + " BLOB,"
                + KEY_FINAL_PRICE + " TEXT,"
                + KEY_MRP + " TEXT,"
                + KEY_CARTQUANTITY + " TEXT,"
                + KEY_TOTALCOUNT + " TEXT,"
                + KEY_CARTID + " TEXT,"
                + KEY_CARTCOUNT + " TEXT,"
                + KEY_UNIT + " TEXT" + ")";


        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_PRODUCTS_DETAILS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS_DETAILS);


        onCreate(db);
    }


    public ProductInfoDetail getProductDetail(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS_DETAILS, new String[] { KEY_SOCIETYPRODUCTID,
                        KEY_TITLE, KEY_CARTQUANTITY,KEY_MRP,KEY_DESCRIPTION }, KEY_SOCIETYPRODUCTID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ProductInfoDetail infoDetail = new ProductInfoDetail();
        infoDetail.setSocietyproductID(Integer.valueOf(cursor.getString(0)));
        infoDetail.setTitle(cursor.getString(1));
        infoDetail.setCartQuantity(Integer.valueOf(cursor.getString(2)));
        infoDetail.setMrp(Float.valueOf(cursor.getString(3)));
        infoDetail.setDescription(cursor.getString(4));

        return infoDetail;
    }

    public void addProduct(ProductListResponseSearchMobile contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SOCIETYPRODUCTID, contact.getSocietyproductID());
        values.put(KEY_TITLE, contact.getTitle());
        values.put(KEY_ORIGINAL_IMAGE, contact.getOriginalImage());
        values.put(KEY_THUMB_IMAGE, contact.getThumbImage());
        values.put(KEY_THUMB_IMAGE_BLOB, contact.getImage_blob());
        values.put(KEY_FINAL_PRICE, contact.getFinalprice());
        values.put(KEY_MRP, contact.getMrp());
        values.put(KEY_CARTQUANTITY, contact.getCartQuantity());
        values.put(KEY_TOTALCOUNT, contact.getTotalCount());
        values.put(KEY_CARTID, contact.getCartId());
        values.put(KEY_CARTCOUNT, contact.getCartCount());
        values.put(KEY_UNIT, contact.getUnit());

        db.insert(TABLE_PRODUCTS, null, values);

        db.close();
    }


    public void addProductDetails(ProductInfoDetail infoDetail) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_SOCIETYPRODUCTID, infoDetail.getSocietyproductID());
        values.put(KEY_PRODUCT_ID, infoDetail.getProductID());
        values.put(KEY_TITLE, infoDetail.getTitle());
        values.put(KEY_CATEGORY_ID, infoDetail.getCategoryID());
        values.put(KEY_SUB_CATEGORY_ID, infoDetail.getSubcategoryID());
        values.put(KEY_CATEGORY_TITLE, infoDetail.getCategoryTitle());
        values.put(KEY_SUB_CATEGORY_TITLE, infoDetail.getSubCategoryTitle());
        values.put(KEY_DESCRIPTION, infoDetail.getDescription());
        values.put(KEY_MRP, infoDetail.getMrp());
        values.put(KEY_FINAL_PRICE, infoDetail.getFinalprice());
        values.put(KEY_UNIT, infoDetail.getUnit());
        values.put(KEY_ADDED_TO_CART, infoDetail.getAddedtoCart());
        values.put(KEY_CARTID, infoDetail.getCartId());
        values.put(KEY_CARTQUANTITY, infoDetail.getCartQuantity());
        values.put(KEY_CARTCOUNT, infoDetail.getCartCount());


        db.insert(TABLE_PRODUCTS_DETAILS, null, values);

        db.close();
    }



    public List<ProductListResponseSearchMobile> getAllProducts() {
        List<ProductListResponseSearchMobile> contactList = new ArrayList<ProductListResponseSearchMobile>();

        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                ProductListResponseSearchMobile responseSearchMobile = new ProductListResponseSearchMobile();
                responseSearchMobile.setSocietyproductID(Integer.parseInt(cursor.getString(1)));
                responseSearchMobile.setTitle(cursor.getString(2));
                responseSearchMobile.setOriginalImage(cursor.getString(3));
                responseSearchMobile.setThumbImage(cursor.getString(4));
                responseSearchMobile.setImage_blob(cursor.getBlob(5));
                responseSearchMobile.setFinalprice(Float.valueOf(cursor.getString(6)));
                responseSearchMobile.setMrp(Float.valueOf(cursor.getString(7)));
                responseSearchMobile.setCartQuantity(Integer.valueOf(cursor.getString(8)));
                responseSearchMobile.setTotalCount(Integer.valueOf(cursor.getString(9)));
                responseSearchMobile.setCartId(Integer.valueOf(cursor.getString(10)));
                responseSearchMobile.setCartCount(Integer.valueOf(cursor.getString(11)));
                responseSearchMobile.setUnit(cursor.getString(12));

                contactList.add(responseSearchMobile);



            } while (cursor.moveToNext());
        }

        return contactList;
    }








}