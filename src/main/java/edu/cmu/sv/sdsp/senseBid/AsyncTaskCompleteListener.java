/**
Copyright (c) 2013 Carnegie Mellon University Silicon Valley.
All rights reserved.

This program and the accompanying materials are made available
under the terms of dual licensing(GPL V2 for Research/Education
purposes). GNU Public License v2.0 which accompanies this distribution
is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

Please contact http://www.cmu.edu/silicon-valley/ if you have any
questions.
*/
package main.java.edu.cmu.sv.sdsp.senseBid;

// TODO: Auto-generated Javadoc
//http://www.jameselsey.co.uk/blogs/techblog/extracting-out-your-asynctasks-into-separate-classes-makes-your-code-cleaner/

/**
 * The listener interface for receiving asyncTaskComplete events.
 * The class that is interested in processing a asyncTaskComplete
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addAsyncTaskCompleteListener<code> method. When
 * the asyncTaskComplete event occurs, that object's appropriate
 * method is invoked.
 *
 * @param <T> the generic type
 * @see AsyncTaskCompleteEvent
 */
public interface AsyncTaskCompleteListener <T>{
      
      /**
       * On task completed.
       *
       * @param result the result
       */
      void onTaskCompleted(T result);
}