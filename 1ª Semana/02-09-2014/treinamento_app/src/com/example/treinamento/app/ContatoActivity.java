package com.example.treinamento.app;

import com.example.treinamento.app.entity.Contact;
import com.example.treinamento.app.service.ContactService;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class ContatoActivity extends ActionBarActivity {

	private EditText name, address, site, phone;
	private RatingBar ratingBar;
	private Button btnSaveContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contato);
	
		retrieveContactDataFromScreen();
		
		 btnSaveContact.setOnClickListener(new OnClickListener() {	
				@Override
				public void onClick(View view) {
					Contact contact = new Contact();
					contact.setName(name.getText().toString());
					contact.setAddress(address.getText().toString());
					contact.setSite(site.getText().toString());
					contact.setPhone(phone.getText().toString());
					contact.setRating(ratingBar.getRating());
					
					ContactService.getInstance().saveContact(contact);
					ContatoActivity.this.finish();
				}
			});
	}

	private void retrieveContactDataFromScreen() {
		name = (EditText) findViewById(R.id.txt_contact_name);
		address = (EditText) findViewById(R.id.txt_contact_address);
		site = (EditText) findViewById(R.id.txt_contact_site);
		phone = (EditText) findViewById(R.id.txt_contact_phone);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar_contact_rating);
		btnSaveContact = (Button) findViewById(R.id.btn_contact_save);
	}
}
