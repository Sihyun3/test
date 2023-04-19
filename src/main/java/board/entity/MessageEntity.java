package board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_message")
public class MessageEntity {
	@Id	//엔티티의 기본키(PK)
	@GeneratedValue(strategy= GenerationType.AUTO)	// 기본키 생성 전략 (DB에서 제공하는 키 생성 전략을 따른다)
	private int messageIdx;
	private int writer;
	private String channelId;
	private String data;
//	@OneToOne
//	@JoinColumn(name = "t_chattingroom")
	private int roomIdx;
}
