package board.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//form에서 전송되는 데이터를 받는 Board이며
//해당객체는 화면을 표현할 때도 사용함
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardForm{
	int id;
	String title;
	String content;
	String attachment;
	String author;
}