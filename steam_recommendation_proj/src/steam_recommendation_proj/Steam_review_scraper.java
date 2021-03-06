package steam_recommendation_proj;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steam_review_scraper {

	public static void do_steam_review_scraper_now(String appid, int review_number, String json_output_path) {

		// 開啟火狐瀏覽器
		WebDriver firefoxdrive = new FirefoxDriver();

		firefoxdrive.get("http://store.steampowered.com/app/" + appid + "/");

		// 取得目前url網址字串
		String url = firefoxdrive.getCurrentUrl();

		// 判斷是否有出現年齡檢查畫面

		if (url.contains("agecheck")) {

			// 自動點擊送出合法年齡
			firefoxdrive.findElement(By.xpath("//select[@id='ageYear']/option[@value='1986']")).click();
			firefoxdrive.findElement(By.xpath("//form[@id='agecheck_form']/a")).click();
		}
		// 以及判斷是否為在Steam商場的有效真實遊戲
		else if (url.equals("http://store.steampowered.com/")) {

			// Debug
			System.out.println("此遊戲為Steam商城的無效遊戲!!因為網址自動導向到Steam首頁");

			// 直接關閉火狐瀏覽器
			firefoxdrive.close();

		}
		// 若Most Helpful 與 Load More按鈕不存在就直接歸類為無效遊戲
		if ((firefoxdrive.findElements(By.id("ReviewsTab_all")).size() == 0
				&& firefoxdrive.findElements(By.id("LoadMoreReviewsall")).size() == 0)
				|| (firefoxdrive.findElements(By.id("ReviewsTab_all")).size() == 0)) {

			// Debug
			System.out.println("此遊戲為Steam商城的無效遊戲!!因為沒有Most Helpful與Load More之按鈕");

			// 直接關閉火狐瀏覽器
			firefoxdrive.close();

			// 確認語言按鈕點擊
		} else if (firefoxdrive.findElements(By.id("language_pulldown")).size() > 0) {

			// 選取介面語言點擊
			firefoxdrive.findElement(By.id("language_pulldown")).click();
			firefoxdrive.findElement(By.linkText("English（英文）")).click();

			// 等待5秒讀取
			try {
				Thread.sleep(5000);
			} catch (Exception e) {

				System.out.println(e);

			}

			// 點擊Most Helpful之遊戲評論
			firefoxdrive.findElement(By.id("ReviewsTab_all")).click();

			// 等待5秒讀取
			try {
				Thread.sleep(5000);
			} catch (Exception e) {

				System.out.println(e);

			}

		}

		// 若評論內容標籤不存在就直接歸類為無效遊戲
		if (firefoxdrive
				.findElements(
						By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"))
				.size() == 0) {
			// Debug
			System.out.println("此遊戲為Steam商城的無效遊戲!!因為根本沒評論");

			// 直接關閉火狐瀏覽器
			firefoxdrive.close();

		} else {

			// **確認必要網頁標籤與條件皆沒問題後進行評論抓取腳本**
			if (firefoxdrive.findElements(By.id("LoadMoreReviewsall")).size() > 0) {

				// 等待標籤讀取之物件(設定10秒)
				WebDriverWait wait = new WebDriverWait(firefoxdrive, 10);

				// 判斷抓到評論數量計數器
				int count = 0;

				// debug從get_allreview_content取出的評論內容index(第幾筆)
				int allreview_content_count = 0;

				// 開始執行評論需求數量判斷處理
				while (true) {

					// 等待2秒讀取
					try {
						Thread.sleep(2000);
					} catch (Exception e) {

						System.out.println(e);

					}

					count = firefoxdrive
							.findElements(By
									.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"))
							.size();
					if (firefoxdrive.findElements(By.id("LoadMoreReviewsall")).size() != 0) {

						// 等待Load More按鈕出現若超過10秒都沒出現就停止程式
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoadMoreReviewsall")));

						// 確定出現後再進行點擊
						firefoxdrive.findElement(By.id("LoadMoreReviewsall")).click();

					}

					System.out.println("目前抓到一共" + count + "個評論");

					if (count == review_number) {

						// 評論文字內容
						List<WebElement> get_allreview_content = firefoxdrive.findElements(By.xpath(
								"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"));
						// 評論作者名稱與profile網址
						List<WebElement> get_allreview_persona_name = firefoxdrive.findElements(By.xpath(
								"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='persona_name']/a"));
						// 評論文字內容網址
						List<WebElement> get_allreview_review_url = firefoxdrive.findElements(By.xpath(
								"html/body/div/div/div/div/div/div/div/div/div/div/div/div[@class='rightcol']/a"));
						// 評論作者名總評論數量
						List<WebElement> get_allreview_review_number = firefoxdrive.findElements(By.xpath(
								"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='num_reviews']/a"));

						// ---------------------------------------------------------------------------------------------//

						// 建立Json Array
						JSONArray review_array = new JSONArray();

						System.out.println("目前抓到一共" + count + "個評論");

						for (int i = 0; i < get_allreview_content.size(); i++) {
							allreview_content_count++;
							System.out.println("第" + allreview_content_count + "個遊戲評論，" + "評論作者為"
									+ get_allreview_persona_name.get(i).getText() + "，評論內容為"
									+ get_allreview_content.get(i).getText() + "，評論網址為"
									+ get_allreview_review_url.get(i).getAttribute("href") + "，評論作者profile網址為"
									+ get_allreview_persona_name.get(i).getAttribute("href") + "，評論作者所有評論數量為"
									+ get_allreview_review_number.get(i).getText());

							// 建立刷新Json物件
							JSONObject review_obj = new JSONObject();
							// user_name 評論作者暱稱
							review_obj.put("user_name", get_allreview_persona_name.get(i).getText());

							// user_profile 評論作者個人檔案之url
							review_obj.put("user_profile", get_allreview_persona_name.get(i).getAttribute("href"));

							// review_number 評論作者總評論數量
							review_obj.put("review_number", get_allreview_review_number.get(i).getText());

							// review_url 評論之url
							review_obj.put("review_url", get_allreview_review_url.get(i).getAttribute("href"));

							// review_content 評論之文字內容
							review_obj.put("review_content", get_allreview_content.get(i).getText());

							// 寫入Json物件與JsonArray

							review_array.add(review_obj);

						}

						try {
							// 建立抓取到遊戲評論的JSON檔
							FileOutputStream fos = new FileOutputStream(json_output_path);
							Writer json_writer = new OutputStreamWriter(fos, "UTF8");

							// 寫入JSON物件
							json_writer.write("{" + "\"steam_review\" :" + review_array.toJSONString() + "}");

							// 關閉寫入
							json_writer.flush();
							json_writer.close();
						} catch (Exception e) {
							System.out.println(e.toString());
						}

						// ---------------------------------------------------------------------------------------------//

						break;

					} else if (count > review_number) {
						// 評論文字內容
						List<WebElement> get_allreview_content = firefoxdrive.findElements(By.xpath(
								"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"));
						// 評論作者名稱與profile網址
						List<WebElement> get_allreview_persona_name = firefoxdrive.findElements(By.xpath(
								"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='persona_name']/a"));
						// 評論文字內容網址
						List<WebElement> get_allreview_review_url = firefoxdrive.findElements(By.xpath(
								"html/body/div/div/div/div/div/div/div/div/div/div/div/div[@class='rightcol']/a"));
						// 評論作者名總評論數量
						List<WebElement> get_allreview_review_number = firefoxdrive.findElements(By.xpath(
								"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='num_reviews']/a"));

						// 建立Json Array
						JSONArray review_array = new JSONArray();

						System.out.println("一開始Size為:" + get_allreview_persona_name.size() + " "
								+ get_allreview_persona_name.size() + " " + get_allreview_content.size() + " "
								+ get_allreview_review_url.size() + " " + get_allreview_review_number.size() + "，count為"
								+ count);
						int count_more = get_allreview_content.size();

						while (true) {

							count_more--;
							get_allreview_content.remove(count_more);
							get_allreview_persona_name.remove(count_more);
							get_allreview_review_url.remove(count_more);
							get_allreview_review_number.remove(count_more);

							System.out.println("目前減掉Size為:" + get_allreview_persona_name.size() + " "
									+ get_allreview_persona_name.size() + " " + get_allreview_content.size() + " "
									+ get_allreview_review_url.size());

							if (count_more == review_number) {

								for (int i = 0; i < get_allreview_content.size(); i++) {
									allreview_content_count++;
									System.out.println("第" + allreview_content_count + "個遊戲評論，" + "評論作者為"
											+ get_allreview_persona_name.get(i).getText() + "，評論內容為"
											+ get_allreview_content.get(i).getText() + "，評論網址為"
											+ get_allreview_review_url.get(i).getAttribute("href") + "，評論作者profile網址為"
											+ get_allreview_persona_name.get(i).getAttribute("href") + "，評論作者所有評論數量為"
											+ get_allreview_review_number.get(i).getText());
									// 建立刷新Json物件
									JSONObject review_obj = new JSONObject();
									// user_name 評論作者暱稱
									review_obj.put("user_name", get_allreview_persona_name.get(i).getText());

									// user_profile 評論作者個人檔案之url
									review_obj.put("user_profile",
											get_allreview_persona_name.get(i).getAttribute("href"));

									// review_number 評論作者總評論數量
									review_obj.put("review_number", get_allreview_review_number.get(i).getText());

									// review_url 評論之url
									review_obj.put("review_url", get_allreview_review_url.get(i).getAttribute("href"));

									// review_content 評論之文字內容
									review_obj.put("review_content", get_allreview_content.get(i).getText());

									// 寫入Json物件與JsonArray

									review_array.add(review_obj);

								}

								try {
									// 建立抓取到遊戲評論的JSON檔
									FileOutputStream fos = new FileOutputStream(json_output_path);
									Writer json_writer = new OutputStreamWriter(fos, "UTF8");

									// 寫入JSON物件
									json_writer.write("{" + "\"steam_review\" :" + review_array.toJSONString() + "}");

									// 關閉寫入
									json_writer.flush();
									json_writer.close();
								} catch (Exception e) {
									System.out.println(e.toString());
								}

								break;

							}

						}

						break;

					} else if (count < review_number
							&& firefoxdrive.findElements(By.className("no_more_reviews")).size() > 0
							&& firefoxdrive.findElements(By.id("LoadMoreReviewsall")).size() == 0) {

						// Debug
						System.out.println("此遊戲為Steam商城的無效遊戲!!因為評論數不足");

						// 直接關閉火狐瀏覽器
						firefoxdrive.close();

						break;

					}

				}

				// 抓取完畢後關閉火狐瀏覽器
				firefoxdrive.close();

			} else {

				// 判斷抓到評論數量計數器
				int count = 0;

				// debug從get_allreview_content取出的評論內容index(第幾筆)
				int allreview_content_count = 0;

				count = firefoxdrive
						.findElements(By
								.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"))
						.size();

				if (count == review_number) {

					// 評論文字內容
					List<WebElement> get_allreview_content = firefoxdrive.findElements(By
							.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"));
					// 評論作者名稱與profile網址
					List<WebElement> get_allreview_persona_name = firefoxdrive.findElements(By.xpath(
							"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='persona_name']/a"));
					// 評論文字內容網址
					List<WebElement> get_allreview_review_url = firefoxdrive.findElements(
							By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div[@class='rightcol']/a"));
					// 評論作者名總評論數量
					List<WebElement> get_allreview_review_number = firefoxdrive.findElements(By.xpath(
							"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='num_reviews']/a"));

					System.out.println("目前抓到一共" + count + "個評論");

					// ---------------------------------------------------------------------------------------------//

					// 建立Json Array
					JSONArray review_array = new JSONArray();

					System.out.println("目前抓到一共" + count + "個評論");

					for (int i = 0; i < get_allreview_content.size(); i++) {
						allreview_content_count++;
						System.out.println("第" + allreview_content_count + "個遊戲評論，" + "評論作者為"
								+ get_allreview_persona_name.get(i).getText() + "，評論內容為"
								+ get_allreview_content.get(i).getText() + "，評論網址為"
								+ get_allreview_review_url.get(i).getAttribute("href") + "，評論作者profile網址為"
								+ get_allreview_persona_name.get(i).getAttribute("href") + "，評論作者所有評論數量為"
								+ get_allreview_review_number.get(i).getText());
						// 建立刷新Json物件
						JSONObject review_obj = new JSONObject();
						// user_name 評論作者暱稱
						review_obj.put("user_name", get_allreview_persona_name.get(i).getText());

						// user_profile 評論作者個人檔案之url
						review_obj.put("user_profile", get_allreview_persona_name.get(i).getAttribute("href"));

						// review_number 評論作者總評論數量
						review_obj.put("review_number", get_allreview_review_number.get(i).getText());

						// review_url 評論之url
						review_obj.put("review_url", get_allreview_review_url.get(i).getAttribute("href"));

						// review_content 評論之文字內容
						review_obj.put("review_content", get_allreview_content.get(i).getText());

						// 寫入Json物件與JsonArray

						review_array.add(review_obj);

					}

					try {
						// 建立抓取到遊戲評論的JSON檔
						FileOutputStream fos = new FileOutputStream(json_output_path);
						Writer json_writer = new OutputStreamWriter(fos, "UTF8");

						// 寫入JSON物件
						json_writer.write("{" + "\"steam_review\" :" + review_array.toJSONString() + "}");

						// 關閉寫入
						json_writer.flush();
						json_writer.close();
					} catch (Exception e) {
						System.out.println(e.toString());
					}

					// ---------------------------------------------------------------------------------------------//

					// 抓取完畢後關閉火狐瀏覽器
					firefoxdrive.close();

				} else if (count > review_number) {
					// 評論文字內容
					List<WebElement> get_allreview_content = firefoxdrive.findElements(By
							.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='content']"));
					// 評論作者名稱與profile網址
					List<WebElement> get_allreview_persona_name = firefoxdrive.findElements(By.xpath(
							"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='persona_name']/a"));
					// 評論文字內容網址
					List<WebElement> get_allreview_review_url = firefoxdrive.findElements(
							By.xpath("html/body/div/div/div/div/div/div/div/div/div/div/div/div[@class='rightcol']/a"));
					// 評論作者名總評論數量
					List<WebElement> get_allreview_review_number = firefoxdrive.findElements(By.xpath(
							"html/body/div/div/div/div/div/div/div/div/div/div/div/div/div[@class='num_reviews']/a"));

					// 建立Json Array
					JSONArray review_array = new JSONArray();

					System.out.println(
							"一開始Size為:" + get_allreview_persona_name.size() + " " + get_allreview_persona_name.size()
									+ " " + get_allreview_content.size() + " " + get_allreview_review_url.size() + " "
									+ get_allreview_review_number.size() + "，count為" + count);
					int count_more = get_allreview_content.size();

					while (true) {

						count_more--;
						get_allreview_content.remove(count_more);
						get_allreview_persona_name.remove(count_more);
						get_allreview_review_url.remove(count_more);
						get_allreview_review_number.remove(count_more);

						System.out.println("目前減掉Size為:" + get_allreview_persona_name.size() + " "
								+ get_allreview_persona_name.size() + " " + get_allreview_content.size() + " "
								+ get_allreview_review_url.size());

						if (count_more == review_number) {

							for (int i = 0; i < get_allreview_content.size(); i++) {
								allreview_content_count++;
								System.out.println("第" + allreview_content_count + "個遊戲評論，" + "評論作者為"
										+ get_allreview_persona_name.get(i).getText() + "，評論內容為"
										+ get_allreview_content.get(i).getText() + "，評論網址為"
										+ get_allreview_review_url.get(i).getAttribute("href") + "，評論作者profile網址為"
										+ get_allreview_persona_name.get(i).getAttribute("href") + "，評論作者所有評論數量為"
										+ get_allreview_review_number.get(i).getText());
								// 建立刷新Json物件
								JSONObject review_obj = new JSONObject();
								// user_name 評論作者暱稱
								review_obj.put("user_name", get_allreview_persona_name.get(i).getText());

								// user_profile 評論作者個人檔案之url
								review_obj.put("user_profile", get_allreview_persona_name.get(i).getAttribute("href"));

								// review_number 評論作者總評論數量
								review_obj.put("review_number", get_allreview_review_number.get(i).getText());

								// review_url 評論之url
								review_obj.put("review_url", get_allreview_review_url.get(i).getAttribute("href"));

								// review_content 評論之文字內容
								review_obj.put("review_content", get_allreview_content.get(i).getText());

								// 寫入Json物件與JsonArray

								review_array.add(review_obj);

							}

							try {
								// 建立抓取到遊戲評論的JSON檔
								FileOutputStream fos = new FileOutputStream(json_output_path);
								Writer json_writer = new OutputStreamWriter(fos, "UTF8");

								// 寫入JSON物件
								json_writer.write("{" + "\"steam_review\" :" + review_array.toJSONString() + "}");

								// 關閉寫入
								json_writer.flush();
								json_writer.close();
							} catch (Exception e) {
								System.out.println(e.toString());
							}

							break;

						}

					}

					// 抓取完畢後關閉火狐瀏覽器
					firefoxdrive.close();

				} else if (count < review_number
						&& firefoxdrive.findElements(By.className("no_more_reviews")).size() > 0
						&& firefoxdrive.findElements(By.id("LoadMoreReviewsall")).size() == 0) {

					// Debug
					System.out.println("此遊戲為Steam商城的無效遊戲!!因為評論數不足");

					// 直接關閉火狐瀏覽器
					firefoxdrive.close();

				}

			}

		}

	}

	public static void main(String[] args) {

		// 執行steam_scraper
		do_steam_review_scraper_now("282350", 53, "C:\\Users\\John-Wall\\Desktop\\Steam_game\\282350.json");

	}

}
