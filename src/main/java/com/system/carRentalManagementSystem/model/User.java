package com.system.carRentalManagementSystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<Booking> bookings = new ArrayList<>();

    @Getter
    @Setter
    private String contactNo;

    @Getter
    @Setter
    @Column(unique = true)
    private String emailAddress;

    @Getter
    @Setter
    private String DOB;

    public User(String name, String contactNo, String emailAddress, String DOB) {
        this.name = name;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.DOB = DOB;
    }

    public Boolean hasBooking(Long bookingId) {
        boolean hasBooking = false;

        for (Booking booking : getBookings()) {
            if (booking.getId().equals(bookingId)) {
                hasBooking = true;
                break;
            }
        }

        return hasBooking;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }

        User that = (User) object;

        return this.name.equals(that.name) && this.contactNo.equals(that.contactNo)
                && this.emailAddress.equals(that.emailAddress) && this.DOB.equals(that.DOB);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + contactNo.hashCode();
        result = 31 * result + emailAddress.hashCode();
        result = 31 * result + DOB.hashCode();

        return result;
    }
}