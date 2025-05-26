package homework.frontend.h55;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VoteRecord {
	/**
	 * fileName是一个投票的明细记录，里面逐行存放了 投票的时间（yyyy-MM-dd HH:mm:ss 格式） +\t+投票的微信ID+\t+候选人
	 * 存放按时间递增（但是可能出现同一秒出现若干条记录的情况）
	 * 现在需要完成投票统计的过程，具体要求如下：
	 * 1个微信ID 1分钟内 最多投1票 多余的票数无效
	 * 1个微信ID 10分钟内 最多只能投5票 多余的票无效
	 * 其中微信ID不固定，候选人姓名不固定
	 * 测试的时候要求10万行记录处理时间不超过3秒 
	 * @param fileName
	 * @return 返回一个map，其中key是候选人名字，value的票数
	 */
	public Map<String, Integer> calcRecording(String fileName) {
		// 存储候选人及其票数
		Map<String, Integer> candidateVotes = new HashMap<>();

		// 存储每个微信ID的投票记录（按时间排序）
		Map<String, List<Long>> wechatVoteRecords = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\t");
				if (parts.length != 3) continue;

				String timeStr = parts[0];
				String wechatId = parts[1];
				String candidate = parts[2];

				try {
					// 解析时间戳为毫秒
					long voteTime = sdf.parse(timeStr).getTime();

					// 获取该微信ID的投票记录
					List<Long> votes = wechatVoteRecords.computeIfAbsent(wechatId, k -> new ArrayList<>());

					// 检查1分钟内是否已投票
					if (!votes.isEmpty()) {
						long lastVote = votes.get(votes.size() - 1);
						if (voteTime - lastVote < TimeUnit.MINUTES.toMillis(1)) {
							continue; // 1分钟内重复投票，无效
						}
					}

					// 检查10分钟内是否已投5票
					if (votes.size() >= 5) {
						long fifthVoteAgo = votes.get(votes.size() - 5);
						if (voteTime - fifthVoteAgo < TimeUnit.MINUTES.toMillis(10)) {
							continue; // 10分钟内已投5票，无效
						}
					}

					// 记录有效投票
					votes.add(voteTime);

					// 统计候选人票数
					candidateVotes.merge(candidate, 1, Integer::sum);

				} catch (ParseException e) {
					// 时间格式错误，跳过该行
					continue;
				}
            }
		} catch (IOException e) {
			e.printStackTrace();
		}

		return candidateVotes;
	}

}
