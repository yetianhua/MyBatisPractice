package bean;

public class CommandContent {
	private Long id;
	private String content;
	private Long command_id;

	@Override
	public String toString() {
		return "CommandContent [id=" + id + ", content=" + content + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCommand_id() {
		return command_id;
	}

	public void setCommand_id(Long command_id) {
		this.command_id = command_id;
	}

}
