package cn.adeerlongneck.app.smartschoolapp.View;

/**
 * Created by 长脖鹿 on 2017/11/15.
 */

public interface LoginView {
  void hideText();
  void showText();
  void loginFail(String error);
  void loginSuccess();
  void showDialog();
  void hideDialog();
  void notRegister();
  void startFaceLogin();
  void isRegister(int i);
}
