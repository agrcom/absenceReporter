package report;

import lombok.NonNull;
import lombok.Value;

@Value
public final class Tuple2<T1, T2> {
    @NonNull
    private T1 item1;
    @NonNull
    private T2 item2;
}
