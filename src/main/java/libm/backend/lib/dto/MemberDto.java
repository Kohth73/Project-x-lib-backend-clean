package libm.backend.lib.dto;

public class MemberDto {
    private String userId;
    private String name;
    private String contact;
    private String address;

    public MemberDto() {}

    public MemberDto(String userId, String name, String contact, String address) {
        this.userId = userId;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
