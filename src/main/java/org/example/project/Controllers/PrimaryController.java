package org.example.project.Controllers;



import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.project.benchmark.cpu.CPUThreadedRoots;
import org.example.project.logging.ConsoleLogger;
import org.example.project.logging.ILogger;
import org.example.project.logging.TimeUnit;
import org.example.project.timing.ITimer;
import org.example.project.timing.Timer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Math.log10;
import static java.lang.Math.sqrt;


public class PrimaryController implements Initializable {
    @FXML
    public Button runButton;

    @FXML
    public ProgressBar progressBar;

    @FXML
    public ChoiceBox nrThreads;

    @FXML
    public TextField workload;

//	public String getSeqWriteResult() {
//		return seqWriteResult;
//	}
//
//	public String getSeqReadResult() {
//		return seqReadResult;
//	}
//
//	public String getRandWriteResult() {
//		return randWriteResult;
//	}
//
//	public String getRandReadResult() {
//		return randReadResult;
//	}
//
	private int  NumberThreads;
	private String  Workload;

	public int getNumberThreads() {

		if (nrThreads.getValue().equals("16")) {
			NumberThreads = 16;
		} else if (nrThreads.getValue().equals("32")) {
			NumberThreads = 32;
		} else if (nrThreads.getValue().equals("64")) {
			NumberThreads = 64;
		} else if (nrThreads.getValue().equals("128")) {
			NumberThreads = 128;
		}

		return NumberThreads;
	}

	public int getWorkload() {
		Workload = workload.getText();
		return Integer.parseInt(Workload);
	}

	CPUThreadedRoots bench = new CPUThreadedRoots();

	@FXML
	private Label label;

	@FXML
	private ChoiceBox<String> partition, size;

	//private Task copyWorker;

	Stage primeStage = null ;

	private void ClosePrimaryWindow() {
		primeStage = (Stage) workload.getScene().getWindow();
		primeStage.close();
	}

	@FXML
	private void Run() {

//		progressBar.setProgress(0.0);
//        Task copyWorker = createWorker();
//
//		progressBar.progressProperty().unbind();
//		progressBar.progressProperty().bind(copyWorker.progressProperty());
//		copyWorker.messageProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
//		copyWorker.messageProperty().addListener((observable, oldValue, newValue) -> label.setText(newValue));
//
//		copyWorker.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//			@Override
//			public void handle(WorkerStateEvent workerStateEvent) {
//				try {
//					AlertBox.display("Finished", "Press the button to see the results");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});


		int tempNrThreads = getNumberThreads();
		int tempWorkload = getWorkload();


		CPUThreadedRoots bench = new CPUThreadedRoots();
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.Sec;

        bench.initialize(tempWorkload);
        bench.warmUp();
		long time = 1;
        for (int i = 1; i <= tempNrThreads; i *= 2) {
            timer.start();
            bench.run(i);
            time = timer.stop();
            log.writeTime("[t=" + i + "] Finished in", time, timeUnit);
        }

        DecimalFormat df = new DecimalFormat("#.###");
        double score = ((sqrt(tempNrThreads)*tempWorkload) *1.0)/log10(time);

		System.out.println(df.format(score));

		///write in fisier


			BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("Score.txt"));
			writer.write(String.valueOf(df.format(score)));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        System.out.println(tempNrThreads);
        System.out.println(tempWorkload);
		System.out.println(tempNrThreads*tempWorkload/time);

		Stage primaryStage = new Stage();

		try {
			Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Secondary.fxml")));
			primaryStage.setTitle("SARE-Benchmark");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}



		ClosePrimaryWindow();
	}






	public void initialize(URL url, ResourceBundle rb) {

		nrThreads.getItems().removeAll();
		nrThreads.getItems().addAll("16", "32", "64", "128");
		nrThreads.getSelectionModel().select("16");

	}


//	public Task createWorker() {
//
//		return new Task() {
//
//			@Override
//			protected Object call() throws Exception {
//				for (int i = 0; i <= 100; i++) {
//					if (i == 0)
//						Thread.sleep(1000);
//					// Thread.sleep(100);
//					Thread.sleep(50);
//					updateMessage("Task Completed : " + (i) + "%");
//					updateProgress(i, 100);
//
//				}
//				return true;
//			}
//		};
//	}

}