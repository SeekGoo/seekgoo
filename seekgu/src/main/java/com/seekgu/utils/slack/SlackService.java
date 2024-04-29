package com.seekgu.utils.slack;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.User;

@Service
public class SlackService {

	static String token;
	static String channelId;
	static MethodsClient client = Slack.getInstance().methods();

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

	public static void sendRecruitmentStartNoti(String nickname) {
		sendMessage(channelId, makeRecruitmentStartNotiMessage(nickname));
	}

	public static void sendRecruitmentCompletionNoti(List<String> slackIdList) {
		slackIdList.forEach(slackId ->
			sendMessage(slackId, makeRecruitmentCompletionNotiMessage()));
	}

	public static void addMemberToChannel(String slackId) {
		try {
			client.conversationsInvite(r -> r
				.token(token)
				.channel(channelId)
				.users(List.of(slackId)));
		} catch (IOException | SlackApiException e) {
			throw new RuntimeException("SLACK_API_FAILURE");
		}
	}

	private static List<User> fetchUsers() {
		try {
			var result = client.usersList(r -> r
				.token(token)
			);
			return result.getMembers();
		} catch (IOException | SlackApiException e) {
			throw new RuntimeException("SLACK_API_FAILURE");
		}
	}

	private static String makeRecruitmentStartNotiMessage(String nickname) {
		return nickname + "님이 식구 모집글을 등록했어요!";
	}

	private static String makeRecruitmentCompletionNotiMessage() {
		return "식구 모집이 완료되었어요!";
	}

	private static void sendMessage(String destinationId, String message) {
		try {
			client.chatPostMessage(r -> r
				.token(token)
				.channel(destinationId)
				.text(message));
		} catch (IOException | SlackApiException e) {
			throw new RuntimeException("SLACK_API_FAILURE");
		}
	}
}
