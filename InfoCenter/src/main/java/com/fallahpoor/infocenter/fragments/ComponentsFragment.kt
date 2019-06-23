/*
    Copyright (C) 2014-2016 Masood Fallahpoor

    This file is part of Info Center.

    Info Center is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Info Center is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Info Center. If not, see <http://www.gnu.org/licenses/>.
 */

package com.fallahpoor.infocenter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import com.fallahpoor.infocenter.R
import com.fallahpoor.infocenter.adapters.ComponentListItem
import com.fallahpoor.infocenter.adapters.CustomArrayAdapter
import com.fallahpoor.infocenter.adapters.ListItem
import kotlinx.android.synthetic.main.fragment_components.*
import java.util.*

/**
 * This Fragment displays a list of components. The components are: General,
 * Android, CPU, Screen, RAM, Storage, Camera, Sensors, Battery, Wi-Fi, GPU
 * Bluetooth, GPS and Sim card. A user can tap a component to see some
 * information about that component.
 */
class ComponentsFragment : Fragment() {

    private val listItems: ArrayList<ListItem>
        get() {

            val items = ArrayList<ListItem>()
            val listItems = resources.getStringArray(R.array.components)
            val listImageIds = resources.obtainTypedArray(R.array.components_icons)

            for (i in listItems.indices) {
                items.add(
                    ComponentListItem(
                        listItems[i],
                        listImageIds.getResourceId(i, 0)
                    )
                )
            }

            listImageIds.recycle()

            return items

        }

    interface ComponentsListener {

        /**
         * Called when user clicks on an item of the ListView of
         * ComponentsFragment
         *
         * @param position the position of the clicked item
         */
        fun onComponentClick(position: Int)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_components, container, false)

        listView.onItemClickListener = ComponentClickListener()
        listView.adapter = CustomArrayAdapter(activity, listItems)

        return view

    }

    private inner class ComponentClickListener : OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            (activity as ComponentsListener).onComponentClick(position)
        }

    }

}