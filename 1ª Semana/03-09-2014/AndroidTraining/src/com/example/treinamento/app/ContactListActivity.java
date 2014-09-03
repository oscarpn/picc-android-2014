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

		listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);

		registerForContextMenu(listViewContatos);

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
		List<Contact> contacts = ContactService.getInstance().listAll();
		ContactAdapter adapter = new ContactAdapter(this, contacts);
		listViewContatos.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lista_contatos, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id) {
		case R.id.action_new_contact:
			Intent intent = new Intent(this, ContatoActivity.class);
			super.startActivity(intent);
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
			Intent intent = new Intent(this, ContatoActivity.class);
			intent.putExtra(ContatoActivity.CONTACT_KEY_, selectedContact);
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
									ContactService.getInstance().deleteContact(
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
