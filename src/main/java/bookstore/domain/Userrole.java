package bookstore.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@EnableAutoConfiguration
public class Userrole {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long userroleid;
    private long userid;
    private String role;

    public Userrole() {
    }

    public Userrole(long userid, String role) {
        this.userid = userid;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Userrole{" +
                "userroleid=" + userroleid +
                ", userid=" + userid +
                ", role='" + role + '\'' +
                '}';
    }

    public long getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(int userroleid) {
        this.userroleid = userroleid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
