/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.amqp.rabbit.listener;

import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.SmartLifecycle;

/**
 * Internal abstraction used by the framework representing a message
 * listener container. Not meant to be implemented externally.
 *
 * @author Stephane Nicoll
 * @author Gary Russell
 * @since 1.4
 */
public interface MessageListenerContainer extends SmartLifecycle {

	/**
	 * Setup the message listener to use. Throws an {@link IllegalArgumentException}
	 * if that message listener type is not supported.
	 * @param messageListener the {@code object} to wrapped to the {@code MessageListener}.
	 */
	void setupMessageListener(MessageListener messageListener);

	/**
	 * @return the {@link MessageConverter} that can be used to
	 * convert {@link org.springframework.amqp.core.Message}, if any.
	 * @deprecated - this converter is not used by the container; it was only
	 * used to configure the converter for a {@code @RabbitListener} adapter.
	 * That is now handled differently. If you are manually creating a listener
	 * container, the converter must be configured in a listener adapter (if
	 * present).
	 */
	@Deprecated
	MessageConverter getMessageConverter();

	/**
	 * Do not check for missing or mismatched queues during startup. Used for lazily
	 * loaded message listener containers to avoid a deadlock when starting such
	 * containers. Applications lazily loading containers should verify the queue
	 * configuration before loading the container bean.
	 * @since 2.1.5
	 */
	default void lazyLoad() {
		// no-op
	}

}
