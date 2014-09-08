package com.example.treinamento.app.widget;

import java.util.List;

import com.example.treinamento.app.R;
import com.example.treinamento.app.domain.Contact;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

	private Activity context;
	private List<Contact> contactList;
	private LayoutInflater layoutInflater;

	public ContactAdapter(Activity context, List<Contact> contactList) {
		super();
		this.context = context;
		this.contactList = contactList;
	}

	@Override
	public int getCount() {
		return contactList.size();
	}

	@Override
	public Contact getItem(int position) {
		return contactList.get(position);
	}

	@Override
	public long getItemId(int position) {
		Contact contact = getItem(position);
		return contact.getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parentView) {

		LayoutInflater layoutInflater = context.getLayoutInflater();
		View layoutItem = layoutInflater.inflate(
				R.layout.contact_list_view_item, parentView, false);
		TextView lblName = (TextView) layoutItem.findViewById(R.id.lblName);
		lblName.setText(getItem(position).getName());
		TextView lblPhone = (TextView) layoutItem.findViewById(R.id.lblPhone);
		lblPhone.setText(getItem(position).getPhone());
		
//		//odd line
//		if(position%2 == 0){
//			layoutItem.setBackgroundColor(context.getResources().getColor(R.color.backgroundOdd));
//		}
		

		return layoutItem;
	}

}
