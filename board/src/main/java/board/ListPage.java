package board;

import java.util.List;

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
public class ListPage {
	int totalCount; 
	int pagePerCount; 
	int totalPage; 
	int requestPage;
	//int startnum; // sql문 때문에 필요했던 것이다
	//int endnum; // sql문 때문에 필요했던 것이다
	int startPage; 
	int endPage;
	boolean isPre;
	boolean isNext;
	List<Writing> list;
}
