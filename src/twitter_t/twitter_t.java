/**
 * 이다창(20241115)
 * Twitter_T 클래스
 * 애플리케이션의 진입점 클래스
 * MainApp을 실행하여 GUI를 초기화하고 애플리케이션을 시작합니다.
 */

package twitter_t;

import javax.swing.SwingUtilities;

public class twitter_t {
    public static void main(String[] args) {
        // 애플리케이션 시작: 로그인 화면 호출
        System.out.println("Twitter_T 애플리케이션 시작");
        SwingUtilities.invokeLater(LoginFrame::new); // 로그인 화면 실행
    }
}
