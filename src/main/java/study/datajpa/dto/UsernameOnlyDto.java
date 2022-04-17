package study.datajpa.dto;

public class UsernameOnlyDto {
    private final String username;

    public String getUsername() {
        return username;
    }

    public UsernameOnlyDto(String username) {
        this.username = username;
    }
}
