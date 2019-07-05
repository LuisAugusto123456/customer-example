package pe.entity.microservice.customer.example.bo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import reactor.core.publisher.Flux;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import pe.entity.microservice.customer.example.dto.Happiness;
import pe.entity.microservice.customer.example.dto.PriceTick;
import pe.entity.microservice.customer.example.dto.SomeFeed;
import pe.entity.microservice.customer.example.dto.SomeListener;
import pe.entity.microservice.customer.example.dto.Todo;
import pe.entity.microservice.customer.example.util.Utils;

/**
 * Example BO
 * 
 * @author lpazd
 *
 */
@Component
public class ExampleBO {

	/**
	 * example flux
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleFlux() {

		Flux<Double> randomGenerator = Flux.range(1, 4).delayElements(Duration.ofMillis(1)).map(i -> Math.random())
				.log();

		randomGenerator.subscribe(number -> System.out.println("Got random number {} : " + number));

		return null;
	}

	/**
	 * example RxJava2
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleRxJava2() {
		Observable<String> howdy = Observable.just("Hello1", "World1");
		howdy.subscribe(System.out::println);

		System.out.println("=========================================");
		List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");
		Observable.just(words).subscribe(System.out::println);

		System.out.println("=========================================");
		Observable.fromIterable(words).subscribe(System.out::println);

		System.out.println("=========================================");
		Observable.range(1, 5).subscribe(System.out::println);

		System.out.println("=========================================");
		Observable.fromIterable(words).zipWith(Observable.range(1, Integer.MAX_VALUE),
				(string, count) -> String.format("%2d. %s", count, string)).subscribe(System.out::println);

		System.out.println("=========================================");
		Observable.fromIterable(words).flatMap(word -> Observable.fromArray(word.split("")))
				.zipWith(Observable.range(1, Integer.MAX_VALUE),
						(string, count) -> String.format("%2d. %s", count, string))
				.subscribe(System.out::println);

		System.out.println("=========================================");
		Observable.fromIterable(words).flatMap(word -> Observable.fromArray(word.split(""))).distinct()
				.zipWith(Observable.range(1, Integer.MAX_VALUE),
						(string, count) -> String.format("%2d. %s", count, string))
				.subscribe(System.out::println);

		System.out.println("=========================================");
		Observable.fromIterable(words).flatMap(word -> Observable.fromArray(word.split(""))).distinct().sorted()
				.zipWith(Observable.range(1, Integer.MAX_VALUE),
						(string, count) -> String.format("%2d. %s", count, string))
				.subscribe(System.out::println);

		return null;
	}

	/**
	 * example Tick Tock
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleTickTock() {
		Observable<Long> fast = Observable.interval(1, TimeUnit.SECONDS);
		Observable<Long> slow = Observable.interval(3, TimeUnit.SECONDS);

		Observable<Long> clock = Observable.merge(slow.filter(tick -> isSlowTickTime()),
				fast.filter(tick -> !isSlowTickTime()));

		clock.subscribe(tick -> System.out.println(new Date()));

		try {
			Thread.sleep(60_000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static long start = System.currentTimeMillis();

	public static Boolean isSlowTickTime() {
		return (System.currentTimeMillis() - start) % 30_000 >= 15_000;
	}

	/**
	 * Is Slow Tick Time
	 * 
	 * @return Is Slow Tick Time
	 */
//	private static boolean isSlowTickTime() {
//		return LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY
//				|| LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY;
//	}
	/**
	 * example Tick Tock
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> examplePriceTickLauncher() {
		SomeFeed<PriceTick> feed = new SomeFeed<>();
		Flowable<PriceTick> flowable = Flowable.create(emitter -> {
			SomeListener listener = new SomeListener() {
				@Override
				public void priceTick(PriceTick event) {
					emitter.onNext(event);
					if (event.isLast()) {
						emitter.onComplete();
					}
				}

				@Override
				public void error(Throwable e) {
					emitter.onError(e);
				}
			};
			feed.register(listener);
		}, BackpressureStrategy.BUFFER);

		ConnectableFlowable<PriceTick> hotObservable = flowable.publish();
		hotObservable.connect();

		hotObservable.take(10).subscribe((priceTick) -> System.out.printf("1 %s %4s %6.2f%n", priceTick.getDate(),
				priceTick.getInstrument(), priceTick.getPrice()));

		Utils.sleep(1000);

		return null;
	}

	/**
	 * example rxJava2 v2
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleRxJava2_v2() {
		Observable<Todo> todoObservable = Observable.create(emitter -> {
			try {
				List<Todo> todos = getTodos();
				for (Todo todo : todos) {
					emitter.onNext(todo);
				}
				emitter.onComplete();
			} catch (Exception e) {
				emitter.onError(e);
			}
		});

		// Simply subscribe with a io.reactivex.functions.Consumer<T>, which will be
		// informed onNext()
		Disposable disposable = todoObservable.subscribe(t -> System.out.println(t));

		// Dispose the subscription when not interested in the emitted data any more
		disposable.dispose();

		// Also handle the error case with a second io.reactivex.functions.Consumer<T>
		Disposable subscribe = todoObservable.subscribe(t -> System.out.println(t), e -> e.printStackTrace());
		subscribe.dispose();
		return null;
	}

	/**
	 * example rxJava2 v3
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleRxJava2_v3() {
		Observable<Todo> todoObservable = Observable.create(emitter -> {
			try {
				List<Todo> todos = getTodos();
				for (Todo todo : todos) {
					emitter.onNext(todo);
				}
				emitter.onComplete();
			} catch (Exception e) {
				emitter.onError(e);
			}
		});

		DisposableObserver<Todo> disposableObserver = todoObservable.subscribeWith(new DisposableObserver<Todo>() {

			@Override
			public void onNext(Todo t) {
				System.out.println("next: " + t);
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("error: " + e);
			}

			@Override
			public void onComplete() {
				System.out.println("complete");
			}
		});
		disposableObserver.dispose();
		return null;
	}

	/**
	 * example rxJava2 v4
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleRxJava2_v4() {
		Single<List<Todo>> todosSingle = Single.just(getTodos());

		Disposable disposable = todosSingle.subscribeWith(new DisposableSingleObserver<List<Todo>>() {

			@Override
			public void onStart() {
				System.out.println("Started");
			}

			@Override
			public void onSuccess(List<Todo> todos) {
				// work with the resulting todos
				todos.stream().forEach((p) -> {
					System.out.println(p.getId());
				});
			}

			@Override
			public void onError(Throwable e) {
				// handle the error case
				System.out.println("Error:" + e);
			}
		});

		// continue working and dispose when value of the Single is not interesting any
		// more
		disposable.dispose();
		return null;
	}

	/**
	 * example rxJava2 v5
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleRxJava2_v5() {
		CompositeDisposable compositeDisposable = new CompositeDisposable();

		Single<List<Todo>> todosSingle = Single.just(getTodos());

		Single<Happiness> happiness = Single.just(new Happiness(100L, "value100"));

		compositeDisposable.add(todosSingle.subscribeWith(new DisposableSingleObserver<List<Todo>>() {

			@Override
			public void onSuccess(List<Todo> todos) {
				// work with the resulting todos
				todos.stream().forEach((p) -> {
					System.out.println(p.getId());
				});
			}

			@Override
			public void onError(Throwable e) {
				// handle the error case
				System.out.println("Error:" + e);
			}
		}));

		compositeDisposable.add(happiness.subscribeWith(new DisposableSingleObserver<Happiness>() {

			@Override
			public void onSuccess(Happiness happiness) {
				// celebrate the happiness :-D
				System.out.println(happiness.getId());
			}

			@Override
			public void onError(Throwable e) {
				System.err.println("Don't worry, be happy! :-P");
			}
		}));

		// continue working and dispose all subscriptions when the values from the
		// Single objects are not interesting any more
		compositeDisposable.dispose();
		return null;
	}

	/**
	 * example rxJava2 v6
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleRxJava2_v6(){
		
		Single<List<Todo>> todosSingle = Single.create(emitter -> {
		    Thread thread = new Thread(() -> {
		        try {
		            List<Todo> todosFromWeb = getTodos();// query a webservice

		            System.out.println("Called 4 times!");

		            emitter.onSuccess(todosFromWeb);
		        } catch (Exception e) {
		            emitter.onError(e);
		        }
		    });
		    thread.start();
		});

//		todosSingle.subscribe(... " Show todos times in a bar chart " ...);
//
//		showTodosInATable(todosSingle);
//
//		todosSingle.subscribe(... " Show todos in gant diagram " ...);
//
//		anotherMethodThatsSupposedToSubscribeTheSameSingle(todosSingle);
		
		
		
		
		
		// cache the result of the single, so that the web query is only done once
		
//		Single<List<Todo>> cachedSingle = todosSingle.cache();
//
//		cachedSingle.subscribe(... " Show todos times in a bar chart " ...);
//
//		showTodosInATable(cachedSingle);
//
//		cachedSingle.subscribe(... " Show todos in gant diagram " ...);
//
//		anotherMethodThatsSupposedToSubscribeTheSameSingle(cachedSingle);

		return null;
	}
	/**
	 * example rxJava2 v7
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleRxJava2_v7(){
		Observable.range(0, 10)
		.buffer(3, 5)
		   .subscribe(System.out::println);
		
		Observable.range(0, 10)
		.buffer(3)
		   .subscribe(System.out::println);
		return null;
	}
	/**
	 * Get Todos
	 * 
	 * @return todo
	 */
	private List<Todo> getTodos() {
		List<Todo> lstTodo = new ArrayList<Todo>();
		lstTodo.add(new Todo(1L, "value1"));
		lstTodo.add(new Todo(2L, "value2"));
		lstTodo.add(new Todo(3L, "value3"));
		lstTodo.add(new Todo(4L, "value4"));
		lstTodo.add(new Todo(5L, "value5"));
		lstTodo.add(new Todo(6L, "value6"));
		lstTodo.add(new Todo(7L, "value7"));
		lstTodo.add(new Todo(8L, "value8"));
		lstTodo.add(new Todo(9L, "value9"));
		lstTodo.add(new Todo(10L, "value10"));
		return lstTodo;
	}

	/**
	 * Get Happiness
	 * 
	 * @return todo
	 */
	private List<Happiness> getHappiness() {
		List<Happiness> lstHappiness = new ArrayList<Happiness>();
		lstHappiness.add(new Happiness(1L, "value1"));
		lstHappiness.add(new Happiness(2L, "value2"));
		lstHappiness.add(new Happiness(3L, "value3"));
		lstHappiness.add(new Happiness(4L, "value4"));
		lstHappiness.add(new Happiness(5L, "value5"));
		lstHappiness.add(new Happiness(6L, "value6"));
		lstHappiness.add(new Happiness(7L, "value7"));
		lstHappiness.add(new Happiness(8L, "value8"));
		lstHappiness.add(new Happiness(9L, "value9"));
		lstHappiness.add(new Happiness(10L, "value10"));
		return lstHappiness;
	}

	/**
	 * example flux
	 * 
	 * @return success response
	 */
	public ResponseEntity<Object> exampleError() {

//		Flux<Double> randomGenerator = Flux.range(1, 4).delayElements(Duration.ofMillis(1)).map(i -> Math.random())
//				.log();
//
//		randomGenerator.subscribe(number -> System.out.println("Got random number {} : " + number));
//
//		Observable<String> stringObservable = Observable.create(emitter -> { Arrays.asList("1"); });
//				 
//		//Se suscribe y en el método onNext() se escribe por consola
//		Disposable disposable = stringObservable.subscribe(t -> System.out.println("t : "+t));
//		//Se suscribe y en el método onNext() se escribe por consola, en el método OnError() se imprime la excepción
//		Disposable subscribe = stringObservable.subscribe(t -> System.out.println("te : "+t), e -> e.printStackTrace());
//				
//		DisposableObserver disposableObserver = stringObservable.subscribeWith(new DisposableObserver() {
//
//			@Override
//			public void onError(Throwable e) {
//			}
//
//			@Override
//			public void onComplete() {
//			}
//
//			@Override
//			public void onNext(Object t) {
//				
//				
//			}
//		});
//		disposableObserver.dispose();

		return null;
	}
}
