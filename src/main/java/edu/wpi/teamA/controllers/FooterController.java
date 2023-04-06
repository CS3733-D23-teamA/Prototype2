package edu.wpi.teamA.controllers;

import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FooterController {
  @FXML ImageView aboutButton;
  @FXML ImageView helpButton;
  @FXML Text timeText;

  public void initialize() throws InterruptedException {
    updateTime();
  }

  private String getTime() {
    int second = LocalDateTime.now().getSecond();
    int minute = LocalDateTime.now().getMinute();
    int hour = LocalDateTime.now().getHour();
    int day = LocalDateTime.now().getDayOfMonth();
    int month = LocalDateTime.now().getMonthValue();
    int year = LocalDateTime.now().getYear();

    String secondStr = Integer.toString(second);
    String minuteStr = Integer.toString(minute);
    String hourStr = Integer.toString(hour);
    if (secondStr.length() == 1) {
      secondStr = "0" + secondStr;
    }
    if (minuteStr.length() == 1) {
      minuteStr = "0" + minuteStr;
    }
    if (hourStr.length() == 1) {
      hourStr = "0" + hourStr;
    }
    String time =
        month + "/" + day + "/" + year + " " + hourStr + ":" + minuteStr + ":" + secondStr;
    return time;
  }

  public void updateTime() throws InterruptedException {
    timeText.setText(getTime());
  }
}
