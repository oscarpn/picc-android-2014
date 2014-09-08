package com.example.treinamento.app;

import java.util.List;
import com.example.treinamento.app.domain.Contact;
import com.example.treinamento.app.service.ContactService;
import com.example.treinamento.app.widget.ContactAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactListActivity extends LifeCycleActivity {
	private Contact selectedContact;
	private ListView listViewContatos;

	@Override
	public String getActivityName() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_lista_contatos);
		getSupportActionBar().setSubtitle(R.string.subtitle_contact_list);

		listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);

		registerForContextMenu(listViewContatos);
		
		listViewContatos.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long arg3) {
				selectedContact = (Contact) adapter
						.getItemAtPosition(position);
			}
			
		});
		

		listViewContatos
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> adapter,
							View view, int posicao, long id) {
						selectedContact = (Contact) adapter
								.getItemAtPosition(posicao);

						return false;
					}

				});

	}

	@Override
	protected void onResume() {
		super.onResume();
		loadContactListView();
	}

	private void loadContactListView() {
		List<Contact> contacts = ContactService.getInstance(this).listAll();
		ContactAdapter adapter = new ContactAdapter(this, contacts);
		listViewContatos.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lista_contatos, menu);
		menu.setGroupVisible(R.id.moreOptions, false);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
			if(selectedContact != null){
				menu.setGroupVisible(R.id.moreOptions , true);
			}
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id) {
		case R.id.action_new_contact:
			Intent intent = new Intent(this, ContactActivity.class);
			super.startActivity(intent);
			break;
		case R.id.action_edit_contact:
			Intent intent2 = new Intent(this, ContactActivity.class);
			intent2.putExtra(ContactActivity.CONTACT_KEY_, selectedContact);
			super.startActivity(intent2);
			break;
		case R.id.action_delete_contact:
			new AlertDialog.Builder(this)
					.setTitle(R.string.dialog_confirm)
					.setMessage(
							getString(R.string.dialog_delete_contact,
									selectedContact.getName()))
					.setPositiveButton(R.string.dialog_yes,
							new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									ContactService.getInstance(ContactListActivity.this).deleteContact(
											selectedContact);
									loadContactListView();
								}
							}).setNeutralButton(R.string.dialog_no, null)
					.create().show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.lista_contatos_context, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_edit_contact:
			Intent intent = new Intent(this, ContactActivity.class);
			intent.putExtra(ContactActivity.CONTACT_KEY_, selectedContact);
			super.startActivity(intent);
			break;
		case R.id.action_delete_contact:
			new AlertDialog.Builder(this)
					.setTitle(R.string.dialog_confirm)
					.setMessage(
							getString(R.string.dialog_delete_contact,
									selectedContact.getName()))
					.setPositiveButton(R.string.dialog_yes,
							new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									ContactService.getInstance(ContactListActivity.this).deleteContact(
											selectedContact);
									loadContactListView();
								}
							}).setNeutralButton(R.string.dialog_no, null)
					.create().show();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}
