package secondApp.jpa.crud.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="cust_name", nullable=false, length=250)
	private String custName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "customers_phones")
	private Set<PhoneNumber> phoneNumbers;
	
	public long getId() {
		return id;
	}



	public String getCustName() {
		return custName;
	}



	public void setCustName(String custName) {
		this.custName = custName;
	}

	


}
