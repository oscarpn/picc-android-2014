package com.example.treinamento.app;

import java.util.Map.Entry;

import com.example.treinamento.app.domain.Contact;
import com.example.treinamento.app.domain.exceptions.BussinessException;
import com.example.treinamento.app.service.ContactService;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class ContatoActivity extends LifeCycleActivity {

	public static final String CONTACT_KEY_ = "CONTACT_KEY";

	private EditText name, address, site, phone;
	private RatingBar ratingBar;
	private Button btnSaveContact;
	private Contact contact;

	@Override
	public String getActivityName() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contato);

		retrieveContactDataFromScreen();
		contact = (Contact) getIntent().getSerializableExtra(CONTACT_KEY_);
		if (contact == null) {
			contact = new Contact();
		} else {
			loadContactOnScreen();
		}
		btnSaveContact.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				setContactFromScreen();

				try {
					ContactService.getInstance().saveContact(contact);
					ContatoActivity.this.finish();
				} catch (BussinessException exception) {
					Drawable errorIcon = getResources().getDrawable(
							R.drawable.ic_error);
					errorIcon.setBounds(0, 0, 50, 50);
					for (Entry<Integer, Integer> error : exception
							.getExceptionMap().entrySet()) {
						EditText errorField = (EditText) findViewById(error
								.getKey());
						errorField.setError(getString(error.getValue()),
								errorIcon);
					}
				}
			}
		});
	}

	private void loadContactOnScreen() {
		name.setText(contact.getName());
		address.setText(contact.getAddress());
		site.setText(contact.getSite());
		phone.setText(contact.getPhone());
		ratingBar.setRating(contact.getRating());
	}

	private void retrieveContactDataFromScreen() {
		name = (EditText) findViewById(R.id.txt_contact_name);
		address = (EditText) findViewById(R.id.txt_contact_address);
		site = (EditText) findViewById(R.id.txt_contact_site);
		phone = (EditText) findViewById(R.id.txt_contact_phone);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar_contact_rating);
		btnSaveContact = (Button) findViewById(R.id.btn_contact_save);
	}

	private void setContactFromScreen() {
		contact.setName(name.getText().toString());
		contact.setAddress(address.getText().toString());
		contact.setSite(site.getText().toString());
		contact.setPhone(phone.getText().toString());
		contact.setRating(ratingBar.getRating());
	}
}
