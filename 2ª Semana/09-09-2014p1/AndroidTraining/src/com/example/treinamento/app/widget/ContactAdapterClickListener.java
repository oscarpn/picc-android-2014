package com.example.treinamento.app.widget;

import com.example.treinamento.app.ContactListActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ContactAdapterClickListener implements OnItemClickListener,
		OnItemLongClickListener {

	private ContactListActivity context;
	
	public ContactAdapterClickListener(ContactListActivity context) {
		this.context = context;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View view, int position,
			long id) {
		context.retrieveSelectedContact(adapter, position);
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		context.retrieveSelectedContact(adapter, position);
	}

}
