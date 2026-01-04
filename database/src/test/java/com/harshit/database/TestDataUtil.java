package com.harshit.database;

import com.harshit.database.domain.Author;
import com.harshit.database.domain.Books;
import org.jspecify.annotations.NonNull;

public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static @NonNull Author createTestAuthorA() {
        return new Author(1L, "Randhir Maan", 40);
    }

    public static @NonNull Author createTestAuthorB() {
        return new Author(2L, "Babbu Maan", 42);
    }

    public static @NonNull Author createTestAuthorC() {
        return new Author(3L, "Sherry Maan", 43);
    }

    public static @NonNull Books CreateBookTest() {
        return new Books("101", "Olive", 1L);
    }

    public static @NonNull Books CreateBookTestB() {
        return new Books("102", "Jopp", 1L);
    }
    public static @NonNull Books CreateBookTestC() {
        return new Books("103", "Peace", 1L);
    }
}
