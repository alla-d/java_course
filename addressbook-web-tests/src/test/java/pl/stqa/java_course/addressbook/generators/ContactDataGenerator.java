package pl.stqa.java_course.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "GroupCount")
  public int count;

  @Parameter (names = "-f", description = "Target file" )
  public String file;

  public static void main (String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
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
