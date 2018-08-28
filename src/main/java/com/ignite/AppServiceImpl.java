package com.ignite;

import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignite.model.Student;

@Service
public class AppServiceImpl implements AppService {

	private static final String CACHE_NAME = "StudentCache";

	@Autowired
	private IgniteClient igniteClient;

	@Override
	public void callCache() {
		ClientCache<Long, Student> cache = igniteClient.getOrCreateCache(CACHE_NAME);

		Long key = 1L;
		Student val = new Student();
		val.setId(key.longValue());
		val.setName("Mime");
		val.setAvg(4.8);

		cache.put(key, val);

		System.out.format(">>> Saved [%s] in the cache.\n", val);

		Student cachedVal = cache.get(key);

		System.out.format(">>> Loaded [%s] from the cache.\n", cachedVal);
		System.out.println("\n\n Values on cache [Name]: " + cache.getName());
	}
}
