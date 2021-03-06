package pl.stqa.java_course.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@XStreamAlias("contact")
@Entity
@Table (name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column (name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column (name = "firstname")
  private String contactName;

  @Expose
  @Column (name = "lastname")
  private String lastName;

  @Expose
  @Column (name="address")
  @Type(type="text")
  private String address;

  @Expose
  @Column (name = "home")
  @Type(type="text")
  private String homePhone;

  @Expose
  @Column (name = "mobile")
  @Type(type="text")
  private String mobilePhone;

  @Expose
  @Column (name = "work")
  @Type(type="text")
  private String workPhone;

  @Expose
  @Column(name="email")
  @Type(type="text")
  private String email;

  @Expose
  @Column(name="email2")
  @Type(type="text")
  private String email2;

  @Expose
  @Column(name="email3")
  @Type(type="text")
  private String email3;

// @Expose
// @Transient
//  private String group;

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Transient
  private String allEmails;

  @Column (name = "photo")
  @Type(type="text")
  private String photo;

  @Transient
  private String allDetails;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();
  //private File photo;

  public File getPhoto() {
    return new File (photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllDetails() {
    return allDetails;
  }

  public ContactData withAllDetails(String allDetails) {
    this.allDetails = normalizeData(allDetails);
    return this;
  }

  private String normalizeData(String allDetails) {
    return allDetails
            .replace("H: ", "")
            .replace("M: ", "")
            .replace("W: ", "")
            .replaceAll("\\n+", "\n");
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withContactName(String contactName) {
    this.contactName = contactName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

//  public ContactData withGroup(String group) {
//    this.group = group;
//    return this;
//  }

  public int getId() {
    return id;
  }

  public String getContactName() {
    return contactName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

//  public String getGroup() {
//    return group;
//  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", contactName='" + contactName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", allEmails='" + allEmails + '\'' +
            //", photo='" + photo + '\'' +
            ", allDetails='" + allDetails + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  public List<String> asString() {
    return Arrays.asList(getContactName() + " " + getLastName(),
            getAddress(), getHomePhone(), getMobilePhone(),
            getWorkPhone(), getEmail(), getEmail2(), getEmail3());
  }

  public String mergeDetails() {

    return asString()
            .stream().filter((s) -> !s.equals(""))
            .map((ContactData::clean))
            .collect(Collectors.joining("\n"));
  }

  public static String clean(String allDetails) {

    return allDetails.replaceAll("\\s", " ").replaceAll("[-()]", "");
  }

  public ContactData inGroup(GroupData group){
    groups.add(group);
    return this;
  }
}
