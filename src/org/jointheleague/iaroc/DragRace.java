package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

import org.jointheleague.erik.irobot.IRobotCreateAdapter;
import org.jointheleague.erik.irobot.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

public class DragRace extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;

    public DragRace(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio, dashboard);
        this.dashboard = dashboard;

    }

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Hello! I'm a Clever Robot!");
        //what would you like me to do, Clever Human?



    }

    /* This method is called repeatedly. */
    //dragrace
    public void loop() throws ConnectionLostException, InterruptedException {
        driveDirect(500, 500);
        SystemClock.sleep(5000);
        dashboard.log("Test code to see if it goes in loop");
        sonar.read();
        dashboard.log(sonar.getRightDistance() + "   " + sonar.getFrontDistance() + "   " + sonar.getLeftDistance());
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);


        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        if (isBumpRight()) {
            driveDirect(-200, -200);
            SystemClock.sleep(500);
            driveDirect(0, 340);
            SystemClock.sleep(1000);

        }


    }
}