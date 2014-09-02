package com.example.treinamento.app;

import java.util.List;

import com.example.treinamento.app.entity.Contact;
import com.example.treinamento.app.service.ContactService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaContatosActivity extends ActionBarActivity {
	private ListView listViewContatos;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 super.setContentView(R.layout.activity_lista_contatos);
		 
		 listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);
		 
		 listViewContatos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
				// TODO Auto-generated method stub
				String message = getString(R.string.clicked_on, adapter.getItemAtPosition(posicao).toString());
				Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
			}
		});
		 
		 listViewContatos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
				String message = getString(R.string.position, posicao);
				// TODO Auto-generated method stub
				Toast.makeText(view.getContext(), message , Toast.LENGTH_SHORT).show();
				return true;
			}
			 
		});
			
	}
	
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			List<Contact> contacts = ContactService.getInstance().listAll();
            ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
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
}
