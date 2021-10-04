package com.dev.iccaka.intervaltimer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TimerActivity extends Activity {

    // Views from activity_timer.xml / Vistas desde activity_timer.xml
    private TextView trainingSetsQuantity;
    private TextView trainingWorkQuantity;
    private TextView trainingRestQuantity;
    private TextView trainingMotivationalText;
    private TextView trainingPausedText;
    private ConstraintLayout thisActivity;
    private Button pauseBtn;
    private Button continueBtn;
    private Button endBtn;
    //========================================================

    // Current parameters / Parámetros actuales
    private int sets;
    private int workSecs;
    private int workMins;
    private int restSecs;
    private int restMins;
    //========================================================

    // Starting parameters that we get from the main activity / Parámetros de inicio que obtenemos de la actividad principal
    private int startingWorkSecs;
    private int startingWorkMins;
    private int startingRestMins;
    private int startingRestSecs;
    //========================================================

    // Parameters when we stop, so we know where to continue from / Parámetros cuando nos detenemos, para que sepamos desde dónde continuar
    private int pausedWorkSecs;
    private int pausedWorkMins;
    private int pausedRestSecs;
    private int pausedRestMins;
    //========================================================

    // The two main timers/Los dos temporizadores principales
    private CountDownTimer workCountDownTimer;
    private CountDownTimer restCountDownTimer;
    //========================================================

    // Sounds when we press the buttons / Suena cuando presionamos los botones
    private MediaPlayer mpToEnd;
    private MediaPlayer mpToFullyEnd;
    private MediaPlayer mpToPause;
    private MediaPlayer mpToRest;
    private MediaPlayer mpToResume;
    private MediaPlayer mpToWork;
    private MediaPlayer mpToTick;
    //========================================================

    // Booleans to keep track if the work timer is currently working or not and if any of the timers has ever been paused
    // Booleanos para realizar un seguimiento de si el temporizador de trabajo está funcionando actualmente o no y si alguno de los temporizadores se ha detenido alguna vez.
    private boolean isWorkOn;
    private boolean hasRestBeenPaused;
    private boolean hasWorkBeenPaused;
    private boolean isRestTimerOff;
    private boolean isPauseStateOn;
    //========================================================

    private void createNotificationChannel() {

        // if the person's current android version is the same or higher than 'Build.VERSION_CODES.O' a.k.a Oreo...
        // si la versión actual de Android de la persona es igual o superior a 'Build.VERSION_CODES.O' a.k.a Oreo ...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // ...create a new notification channel named 'Timer' and with an ID of 'oneAndOnly'
            // ... crea un nuevo canal de notificación llamado 'Timer' y con un ID de 'oneAndOnly'.
            NotificationChannel channel = new NotificationChannel("oneAndOnly", "Timer", NotificationManager.IMPORTANCE_HIGH);
            // set a description so the user knows what this specific channel does
            // establecer una descripción para que el usuario sepa qué hace este canal específico.
            channel.setDescription("Timer");

            // using the 'NotificationManager', if it exists, add the newly created channel
            // usando el 'NotificationManager', si existe, agregue el canal recién creado.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    // Methods to start the 2 timers / Métodos para iniciar los 2 temporizadores
    private void startWorkTimer() {

        // if the rest timer hasn't been started and the remaining sets are 0, stop the timer
        // Si el temporizador de descanso no se ha iniciado y los conjuntos restantes son 0, deténgalo..
        if (this.isRestTimerOff && this.sets == 0) {
            this.endTimerAfterCompletion();
            return;
        }

        // the work timer is going to be started, so we set 'isWorkOn' to true
        // el temporizador de trabajo se iniciará, por lo que establecemos 'isWorkOn' en verdadero.
        this.isWorkOn = true;

        // set the background color to red, so we can differentiate between the work and the rest phase
        // establecer el color de fondo en rojo, para que podamos diferenciar entre el trabajo y la fase de descanso.
        this.thisActivity.setBackgroundColor(Color.RED);

        // start the proper sound so we know when to work / iniciar el sonido adecuado para que sepamos cuándo trabajar
        this.mpToWork.start();

        this.updateCurrentWithStartingFields();

        // if this timer has ever been paused - reset the parameters to their default values from MainActivity
        if (this.hasWorkBeenPaused) {
            this.workSecs = this.startingWorkSecs;
            this.workMins = this.startingWorkMins;

            this.workCountDownTimer = new CountDownTimer((((this.workMins * 60) + this.workSecs) * 1000), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // every second decrement the work seconds
                    decrementWork(trainingWorkQuantity);
                }

                @Override
                public void onFinish() {
                    // at the end decrement the sets and start the rest timer
                    decrementSets(trainingSetsQuantity);
                    startRestTimer();
                }
            };

            // then the timer should act like it hasn't been paused before
            this.hasWorkBeenPaused = false;
        }

        // set the visibility to the rest timer text to 'GONE'
        this.trainingRestQuantity.setVisibility(View.GONE);
        // set the visibility to the working timer text to 'VISIBLE'
        this.trainingWorkQuantity.setVisibility(View.VISIBLE);

        // change the text so we know when to start working out
        this.trainingMotivationalText.setText(R.string.text_motivational_timer);

        // finally start the work timer
        this.workCountDownTimer.start();

    }

    private void startRestTimer() { // A method used to start the rest timer a.k.a the timer stars when it's time for you to do a lightweight exercise, for example

        if (this.isRestTimerOff) {
            this.startWorkTimer();
            return;
        }

        // right now the 'work' timer isn't working, so we set 'isWorkOn' to false
        this.isWorkOn = false;

        // set the background color to green, so we can differentiate between 'work' and 'rest' phase
        this.thisActivity.setBackgroundColor(Color.GREEN);

        // start the proper sound so we know when to rest
        this.mpToRest.start();

        this.updateCurrentWithStartingFields();

        // if this timer has ever been paused - reset the parameters to their default values from MainActivity
        if (this.hasRestBeenPaused) {
            this.restSecs = this.startingRestSecs;
            this.restMins = this.startingRestMins;

            this.restCountDownTimer = new CountDownTimer((((this.restMins * 60) + this.restSecs) * 1000), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // every second decrement the work seconds
                    decrementRest(trainingRestQuantity);
                }

                @Override
                public void onFinish() {
                    // at the end if there aren't anymore sets - end the timer a.k.a end the whole activity since we don't have any work to do
                    if (sets > 0) {
                        // ... or just start the work timer and continue with the work
                        startWorkTimer();
                    } else {
                        endTimer(endBtn);
                    }
                }
            };

            // then the timer should act like it hasn't been paused before
            this.hasRestBeenPaused = false;
        }

        // set the visibility to the working timer text to 'GONE'
        this.trainingWorkQuantity.setVisibility(View.GONE);
        // set the visibility to the rest timer text to 'VISIBLE'
        this.trainingRestQuantity.setVisibility(View.VISIBLE);

        // change the text so we know when to rest
        this.trainingMotivationalText.setText(R.string.text_motivational_timer_2);

        // finally start the rest timer
        this.restCountDownTimer.start();

    }
    //========================================================

    // Methods to properly decrement the parameters when you click on their corresponding buttons
    private void decrementWork(View view) {
        // if the seconds of the 'work' timer are higher or equal to zero
        if (this.workSecs >= 0) {
            // if the 'work' seconds are less than or equal to four and if the 'work' minutes are equal to zero
            if (this.workSecs <= 4 && this.workMins == 0) {
                // start the 'tick' sound which informs us about the 3 last seconds
                this.mpToTick.start();
            }
            // decrement the 'work' seconds
            this.workSecs--;

            // if the 'work' seconds are equal to minus one
            if (this.workSecs == -1) {
                // set the seconds to 59 and decrement the minutes
                this.workSecs = 59;
                this.workMins--;

                // if the 'work' minutes are equal to minus one
                if (this.workMins == -1) {
                    // seth both of the seconds and the minutes to zero
                    this.workSecs = 0;
                    this.workMins = 0;
                }
            }
        }

        // finally invoke the custom method 'updateWork' so everything on the screen s up-to-date
        this.updateWork();
    }

    private void decrementRest(View view) {
        // if the seconds of the 'rest' timer are higher or equal to zero
        if (this.restSecs >= 0) {
            // if the 'rest' seconds are less than or equal to four and if the 'rest' minutes are equal to zero
            if (this.restSecs <= 4 && this.restMins == 0) {
                // start the 'tick' sound which informs us about the 3 last seconds
                this.mpToTick.start();
            }
            // decrement the 'work' seconds
            this.restSecs--;

            // if the 'rest' seconds are equal to minus one
            if (this.restSecs == -1) {
                // set the seconds to 59 and decrement the minutes
                this.restSecs = 59;
                this.restMins--;

                // if the 'rest' minutes are equal to minus one
                if (this.restMins == -1) {
                    // seth both of the seconds and the minutes to zero
                    this.restSecs = 0;
                    this.restMins = 0;
                }
            }
        }

        this.updateRest();
    }

    private void decrementSets(View view) {  // A super simple method which just decrements the sets
        this.sets--;

        this.updateSets();
    }
    //========================================================

    // Custom methods to get the parameters on the screen
    private void updateData() {  // A bigger method that 'concatenates' the 3 other smaller ones, which update the data on the screen, so it doesn't take a lot of space when you try to invoke all of them at once
        this.updateSets();
        this.updateWork();
        this.updateRest();
    }

    @SuppressLint("SetTextI18n")
    private void updateSets() {
        if (this.sets > 9) {
            this.trainingSetsQuantity.setText("" + this.sets);
            return;
        }

        this.trainingSetsQuantity.setText("0" + this.sets);
    }

    @SuppressLint("SetTextI18n")
    private void updateWork() {
        if (this.workMins > 9 && this.workSecs > 9) {
            this.trainingWorkQuantity.setText("" + this.workMins + " : " + this.workSecs);
        } else if (this.workMins > 9) {
            this.trainingWorkQuantity.setText("" + this.workMins + " : 0" + this.workSecs);
        } else if (this.workSecs > 9) {
            this.trainingWorkQuantity.setText("0" + this.workMins + " : " + this.workSecs);
        } else {
            this.trainingWorkQuantity.setText("0" + this.workMins + " : 0" + this.workSecs);
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateRest() {
        if (this.restMins > 9 && this.restSecs > 9) {
            this.trainingRestQuantity.setText("" + this.restMins + " : " + this.restSecs);
        } else if (this.restMins > 9) {
            this.trainingRestQuantity.setText("" + this.restMins + " : 0" + this.restSecs);
        } else if (this.restSecs > 9) {
            this.trainingRestQuantity.setText("0" + this.restMins + " : " + this.restSecs);
        } else {
            this.trainingRestQuantity.setText("0" + this.restMins + " : 0" + this.restSecs);
        }
    }
    //========================================================

    private void createNotification() {

        // TODO learn about notification creation via the android developers website; redo the work here and add appropriate comments to the code

        Intent intent = new Intent(this, TimerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "oneAndOnly")
                .setSmallIcon(R.drawable.ic_stat_paused_app)
                .setContentTitle("Sample title")
                .setContentText("Sample text")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);  // Open this activity when the notification is pressed

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify("oneAndOnly", 1, mBuilder.build());
    }

    private void updateCurrentWithStartingFields() {  // A method that just assigns the starting values of the parameters to the parameters
        this.workSecs = this.startingWorkSecs;
        this.workMins = this.startingWorkMins;
        this.restSecs = this.startingRestSecs;
        this.restMins = this.startingRestMins;
    }

    private void updateStartingWithCurrentFields() {  // A method that assigns proper values to the 'starting' parameters, so we always know from where we've started
        this.startingWorkSecs = this.workSecs;
        this.startingWorkMins = this.workMins;
        this.startingRestSecs = this.restSecs;
        this.startingRestMins = this.restMins;
    }

    private void updatePausedFields() {  // A method that gets invoked once we pause any of the timers and just reassigns the values to the parameters
        this.pausedWorkSecs = ++this.workSecs;
        this.pausedWorkMins = this.workMins;
        this.pausedRestSecs = ++this.restSecs;
        this.pausedRestMins = this.restMins;
    }

    private void endTimer(View view) {
        // we stop the timers, no matter who is currently working
        this.stopCurrentTimer();

        // start the proper sound so we know when it has ended a.k.a feedback
        this.mpToEnd.start();

        // end the timer by calling the super method 'onBackPressed' which handles the situation just fine
        super.onBackPressed();
    }

    private void endTimerAfterCompletion() {
        // start the appropriate sound file, so the user knows that the current workout has been successfully completed
        this.mpToFullyEnd.start();

        // we even create a 'Toast' for the user, so he/she knows that everything is completely done
        Toast.makeText(this.getApplicationContext(), "Workout done!", Toast.LENGTH_SHORT).show();

        // end the timer by calling the super method 'onBackPressed' which handles the situation just fine
        super.onBackPressed();
    }

    private void stopCurrentTimer() {
        // stop the currently working timer
        if (this.isWorkOn) {
            this.workCountDownTimer.cancel();
        } else {
            this.restCountDownTimer.cancel();
        }
    }

    private void assignDefaultBooleanValues() { // This method initializes the booleans with their default values
        this.isWorkOn = true;
        this.hasRestBeenPaused = false;
        this.hasWorkBeenPaused = false;
        this.isRestTimerOff = false;
        this.isPauseStateOn = false;
    }

    private void assignDefaultViewVisibilities() {  // This method sets a bunch of different visibilities to the views, so we don't see redundant stuff on the screen
        this.endBtn.setVisibility(View.GONE);
        this.continueBtn.setVisibility(View.GONE);
        this.trainingPausedText.setVisibility(View.GONE);
    }

    private void getViews() {  // This method gets all of the views from 'activity_main.xml' using the 'R' class
        this.continueBtn = findViewById(R.id.continueBtn);
        this.pauseBtn = findViewById(R.id.pauseBtn);
        this.endBtn = findViewById(R.id.endBtn);
        this.thisActivity = findViewById(R.id.timerActivity);
        this.trainingSetsQuantity = findViewById(R.id.trainingSetsQuantity);
        this.trainingWorkQuantity = findViewById(R.id.trainingWorkQuantity);
        this.trainingRestQuantity = findViewById(R.id.trainingRestQuantity);
        this.trainingMotivationalText = findViewById(R.id.trainingMotivationalText);
        this.trainingPausedText = findViewById(R.id.trainingPausedText);
    }

    private void getSounds() {  // This method gets the proper sounds from the 'res/raw' folder and assign them to the corresponding fields
        this.mpToWork = MediaPlayer.create(this.getApplicationContext(), R.raw.work);
        this.mpToRest = MediaPlayer.create(this.getApplicationContext(), R.raw.rest);
        this.mpToPause = MediaPlayer.create(this.getApplicationContext(), R.raw.pause);
        this.mpToResume = MediaPlayer.create(this.getApplicationContext(), R.raw.resume);
        this.mpToEnd = MediaPlayer.create(this.getApplicationContext(), R.raw.end);
        this.mpToFullyEnd = MediaPlayer.create(this.getApplicationContext(), R.raw.fullyend);
        this.mpToTick = MediaPlayer.create(this.getApplicationContext(), R.raw.tick);
    }

    private void setTypeFacesToViews() {  // This method gets invoked if the user's android version is too low to handle XML fonts, it assigns the font faces manually
        Typeface tf = ResourcesCompat.getFont(getApplicationContext(), R.font.monkey);
        this.continueBtn.setTypeface(tf);
        this.pauseBtn.setTypeface(tf);
        this.endBtn.setTypeface(tf);
        this.trainingSetsQuantity.setTypeface(tf);
        this.trainingWorkQuantity.setTypeface(tf);
        this.trainingRestQuantity.setTypeface(tf);
        this.trainingMotivationalText.setTypeface(tf);
        this.trainingPausedText.setTypeface(tf);
    }

    private void getBundleExtrasFromMainActivity() {
        // get the 'Bundle' that was passed to us from the MainActivity class a.k.a get the values of the parameters so we know how long the timers should be
        Bundle mainActivityBundle = getIntent().getExtras();
        if (mainActivityBundle != null) {  // if it's empty...
            this.sets = mainActivityBundle.getInt("sets");
            this.workSecs = mainActivityBundle.getInt("workSecs") + 1;
            this.workMins = mainActivityBundle.getInt("workMins");
            this.restSecs = mainActivityBundle.getInt("restSecs") + 1;
            this.restMins = mainActivityBundle.getInt("restMins");
        } else {  // ... exit the activity using the super method 'onBackPressed'
            super.onBackPressed();
        }
    }

    private void hideStatusBar() {  // A method that just hides the status bar for us, so we don't see it
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {  // The things here should happen only once in the activity's entire lifespan
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        this.getBundleExtrasFromMainActivity();
        this.assignDefaultBooleanValues();

        if (this.restMins == 0 && this.restSecs == 1) {
            this.isRestTimerOff = true;
        }

        this.updateStartingWithCurrentFields();
        this.getSounds();
        this.getViews();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            this.setTypeFacesToViews();
        }

        this.assignDefaultViewVisibilities();
        this.createNotificationChannel();

        // create the two timers a.k.a work and rest
        this.workCountDownTimer = new CountDownTimer((((this.workMins * 60) + this.workSecs) * 1000), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // every second decrement the work seconds
                decrementWork(trainingWorkQuantity);
            }

            @Override
            public void onFinish() {
                // at the end decrement the sets and start the rest timer
                decrementSets(trainingSetsQuantity);
                startRestTimer();
            }
        };
        this.restCountDownTimer = new CountDownTimer((((this.restMins * 60) + this.restSecs) * 1000), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // every second decrement the work seconds
                decrementRest(trainingRestQuantity);
            }

            @Override
            public void onFinish() {
                // at the end if there aren't anymore sets - end the timer a.k.a end the whole activity since we don't have any work to do
                if (sets > 0) {
                    // ... or just start the work timer and continue with the work
                    startWorkTimer();
                } else {
                    endTimerAfterCompletion();
                }
            }
        };

        // set an event to the 'end' button, because it has to be a long, not a normal one
        this.endBtn.setOnLongClickListener(v -> {
            this.endTimer(new View(this.getApplicationContext()));
            return false;
        });

        this.updateData();

        // finally start the work timer
        this.startWorkTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.hideStatusBar();
    }

    @Override
    protected void onStop() {
        super.onStop();

//        this.createNotification();
    }

    // Methods to continue or pause the timers
    public void continueTimer(View view) {
        this.mpToResume.start();

        this.isPauseStateOn = false;

        if (this.isWorkOn) {
            this.workCountDownTimer = new CountDownTimer((((this.pausedWorkMins * 60) + this.pausedWorkSecs + 1) * 1000), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    decrementWork(trainingWorkQuantity);
                    updateWork();
                }

                @Override
                public void onFinish() {
                    decrementSets(trainingSetsQuantity);
                    updateSets();
                    startRestTimer();
                }
            };
            this.workCountDownTimer.start();

            this.thisActivity.setBackgroundColor(Color.RED);
        } else {
            this.restCountDownTimer = new CountDownTimer((((this.pausedRestMins * 60) + this.pausedRestSecs + 1) * 1000), 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    decrementRest(trainingRestQuantity);
                    updateRest();
                }

                @Override
                public void onFinish() {
                    if (sets > 0) {
                        startWorkTimer();
                    } else {
                        endTimer(endBtn);
                    }
                }
            };
            this.restCountDownTimer.start();

            this.thisActivity.setBackgroundColor(Color.GREEN);
        }

        this.continueBtn.setVisibility(View.GONE);
        this.endBtn.setVisibility(View.GONE);
        this.trainingPausedText.setVisibility(View.GONE);
        this.trainingMotivationalText.setVisibility(View.VISIBLE);
        this.pauseBtn.setVisibility(View.VISIBLE);
    }

    public void pauseTimer(View view) {

        this.isPauseStateOn = true;

        // set the background color of the activity to yellow, so we know that the 'pause' phase is on
        this.thisActivity.setBackgroundColor(Color.YELLOW);

        // play the proper sound so we know that we have paused the timer
        this.mpToPause.start();

        // stop the timer that is currently working and assign 'true' to its corresponding 'beenPaused' boolean
        if (this.isWorkOn) {
            this.workCountDownTimer.cancel();
            this.hasWorkBeenPaused = true;
        } else {
            this.restCountDownTimer.cancel();
            this.hasRestBeenPaused = true;
        }

        // set a bunch of different visibilities to the text and button views, so we don't see redundant stuff on the screens
        this.pauseBtn.setVisibility(View.GONE);
        this.trainingMotivationalText.setVisibility(View.GONE);
        this.continueBtn.setVisibility(View.VISIBLE);
        this.endBtn.setVisibility(View.VISIBLE);
        this.trainingPausedText.setVisibility(View.VISIBLE);

        // invoke the 'updatePausedFields' method to assign the proper values to our parameters
        this.updatePausedFields();
    }
    //========================================================

    // Method invoked once we press the 'back' button on our phones
    @Override
    public void onBackPressed() {
        // if any of the timers are currently paused, end this activity by invoking the custom 'endTimer' method
        if (this.isPauseStateOn) {
            this.endTimer(this.endBtn);
            return;
        }
        // if it's not, just pause it using the custom method
        this.pauseTimer(this.pauseBtn);
    }

}