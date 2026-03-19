package board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Writing {

	int id;
	String title;
	String author;
	Date createdate;
	String content;
	String attachment;
	int viewcnt;
	String type;
}
