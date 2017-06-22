## Samples for RxJava 2.0 and RxKotlin

This is a collection of samples demonstrating the use of operators in RxJava 2.0 and RxKotlin.

To contribute, please check the [wiki](https://github.com/moldedbits/rx_operators/wiki)

Here's the list of operators that have been finished

#### Creating observables

| Operator | RxJava 2.x | RxKotlin |
|----------|------------|----------|
| [Create][1] | create, generate, unsafeCreate | create |
| [Defer][2] | defer | defer |
| [Empty/Never/Throw][3] | empty, never, error | empty, never , error |
| [From][9] | from | from |
| [Interval][10] | interval | interval |
| [Just][11] | just | |
| [Range][12] | range | |
| [Repeat][13] | repeat | |

#### Transforming observables

| Operator | RxJava 2.x | RxKotlin |
|----------|------------|----------|
| [Map][5] | map | map |

#### Filtering observables

TODO

#### Combining observables

TODO

#### Error handling operators

TODO

#### Observable utility operators

| Operator | RxJava 2.x | RxKotlin |
|----------|------------|----------|
| [Delay][8] | delay, delaySubscription | delay, delaySubscription |
| [Timestamp][4] | timestamp | timestamp |
| [SubscribeOn][6] | subscribeOn | subscribeOn |
| [ObserveOn][7] | observeOn | observeOn |

#### Conditional and boolean operators

TODO

#### Mathematical and Aggregate operators

TODO

#### Backpressure operators

TODO

#### Connectable obserable operators

TODO

#### Operators to convert observables

TODO

[1]: http://reactivex.io/documentation/operators/create.html
[2]: http://reactivex.io/documentation/operators/defer.html
[3]: http://reactivex.io/documentation/operators/empty-never-throw.html
[4]: http://reactivex.io/documentation/operators/timestamp.html
[5]: http://reactivex.io/documentation/operators/map.html
[6]: http://reactivex.io/documentation/operators/subscribeon.html
[7]: http://reactivex.io/documentation/operators/observeon.html
[8]: http://reactivex.io/documentation/operators/delay.html
[9]: http://reactivex.io/documentation/operators/from.html
[10]: http://reactivex.io/documentation/operators/interval.html
[11]: http://reactivex.io/documentation/operators/just.html
[12]: http://reactivex.io/documentation/operators/range.html
[13]: http://reactivex.io/documentation/operators/repeat.html
