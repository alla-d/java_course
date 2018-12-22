package pl.stqa.java_course.addressbook.generators;

import pl.stqa.java_course.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main (String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s\n", contact.getContactName(), contact.getLastName(), contact.getAddress()
      , contact.getHomePhone(), contact.getEmail(), contact.getGroup()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData().withContactName(String.format("name %s", i)).withLastName(String.format("surname %s", i))
              .withAddress(String.format("address %s", i)).withHomePhone(String.format("111 %s", i))
              .withEmail(String.format("aaa %s", i)).withGroup(String.format("[none] %s", i)));
    }
        return contacts;
  }
}
