package com.monds.date.substitutor;

import java.time.LocalDateTime;

public interface DateResolver {

    LocalDateTime resolve(LocalDateTime ldt, String lookup);
}
