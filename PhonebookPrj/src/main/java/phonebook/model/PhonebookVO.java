package phonebook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PhonebookVO {
	private int id;
	private String name;
	private String hp;
	private String email;
	private String memo;
	private String pic;
	

}
