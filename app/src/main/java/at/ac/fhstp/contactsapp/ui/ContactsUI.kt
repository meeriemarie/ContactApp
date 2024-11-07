package at.ac.fhstp.contactsapp.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import at.ac.fhstp.contactsapp.data.Contact
import at.ac.fhstp.contactsapp.ui.theme.Typography

@Composable
fun ContactsApp(
    modifier: Modifier = Modifier,
    contactsViewModel: ContactsViewModel = viewModel(factory = AppViewModelProviderFactory.Factory)) {
    val state by contactsViewModel.contactsUiState.collectAsStateWithLifecycle()

    Log.i("ContactsApp", "Selected: ${state.selectedCardIndex}")

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // We use lazy column for dynamic lists or large lists
        // it will only draw the visible contacts

        Button(onClick = {
            contactsViewModel.onAddButtonClicked()
        }) {
            Text("Add new contact!")
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            itemsIndexed(state.contacts) { index, contact ->
                if (index == state.selectedCardIndex) {
                    ContactDetails(contact)
                } else {
                    ContactListItem(contact, onCardClick = {
                        contactsViewModel.onCardClick(index)
                    })
                }
            }
        }
        /*Column {
            state.contacts.forEach {
                ContactListItem(it)
            }
        } */
    }
}

@Composable
fun ContactListItem(contact: Contact, onCardClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedCard(onClick = { onCardClick() }, modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(contact.name, style = Typography.headlineMedium)
        }
    }
}

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    OutlinedCard(modifier.fillMaxWidth()
        .padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(contact.name, style = Typography.headlineMedium)
            Row {
                Text("Tel: ${contact.telephoneNumber}", style = Typography.headlineSmall)
                Spacer(Modifier.width(16.dp))
                Text("Age: ${contact.age}", style = Typography.headlineSmall)
            }
        }
    }
}

@Preview
@Composable
private fun ContactDetailsPreview() {
    ContactDetails(Contact(0,"Andrea", "+4354897", 28))
}

@Preview
@Composable
private fun ContactListItemPreview() {
    ContactListItem(Contact(0,"Andrea", "+4354897", 28), {})
}