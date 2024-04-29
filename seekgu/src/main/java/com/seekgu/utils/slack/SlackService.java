package com.seekgu.utils.slack;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.User;

@Service
public class SlackService {

	static String token;
	static String channelId;

	@Value(value = "${slack.token}")
	private void setToken(String value) {
		token = value;
	}

	@Value(value = "${slack.channel-id}")
	private void setChannelId(String value) {
		channelId = value;
	}

	public static String getSlackIdByMemberName(String memberName) {
		List<User> users = fetchUsers();
		Optional<String> userSlackId = Optional.empty();
		for(User user : users) {
			if(user.getProfile().getRealName().equals(memberName)) {
				userSlackId = Optional.of(user.getId());
			}
		}

		return userSlackId.orElseThrow(() -> {
			throw new RuntimeException("SLACK_ID_NOT_FOUND");
		});

	}

	private static List<User> fetchUsers() {
		var client = Slack.getInstance().methods();
		try {
			var result = client.usersList(r -> r
				.token(token)
			);
			return result.getMembers();
		} catch (IOException | SlackApiException e) {
			throw new RuntimeException("SLACK_API_FAILURE");
		}
	}
}
