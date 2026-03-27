package board.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import member.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardPage{
int id;
String title;
String username;
Date createdate;
String content;
String attachment;
int viewcnt;
}